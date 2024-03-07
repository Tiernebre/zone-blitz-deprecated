package com.tiernebre.authentication.google;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.tiernebre.authentication.account.Account;
import com.tiernebre.authentication.account.AccountService;
import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionService;
import io.vavr.control.Either;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.UUID;
import java.util.function.Consumer;
import org.junit.Test;

public final class GoogleAuthenticationStrategyTest {

  private final GoogleIdTokenVerifier verifier = mock(
    GoogleIdTokenVerifier.class
  );
  private final SessionService sessionService = mock(SessionService.class);
  private final AccountService accountService = mock(AccountService.class);
  private final GoogleAuthenticationStrategy googleAuthenticationStrategy =
    new GoogleAuthenticationStrategy(verifier, sessionService, accountService);

  private final record Case(
    String name,
    GoogleAuthenticationRequest request,
    Either<String, Session> expected,
    Consumer<GoogleAuthenticationRequest> mock
  ) {}

  @Test
  public void cases() throws GeneralSecurityException, IOException {
    var tests = new Case[] {
      new Case(
        "Null request",
        null,
        Either.left("Request received was null."),
        null
      ),
      new Case(
        "No Body CSRF Token",
        new GoogleAuthenticationRequest("creds", null, "csrf"),
        Either.left("Request has invalid CSRF tokens."),
        null
      ),
      new Case(
        "No Cookie CSRF Token",
        new GoogleAuthenticationRequest("creds", "csrf", null),
        Either.left("Request has invalid CSRF tokens."),
        null
      ),
      new Case(
        "No Body and Cookie CSRF Token",
        new GoogleAuthenticationRequest("creds", null, null),
        Either.left("Request has invalid CSRF tokens."),
        null
      ),
      new Case(
        "Body and Cookie CSRF Tokens are not equal",
        new GoogleAuthenticationRequest("creds", "body", "cookie"),
        Either.left("Request has invalid CSRF tokens."),
        null
      ),
      new Case(
        "No Credential",
        new GoogleAuthenticationRequest(null, "csrf", "csrf"),
        Either.left(
          "Could not verify and parse given Google authentication credential."
        ),
        null
      ),
      new Case(
        "Google token verifier returns null",
        new GoogleAuthenticationRequest("creds", "csrf", "csrf"),
        Either.left(
          "Could not verify and parse given Google authentication credential."
        ),
        request -> {
          try {
            when(verifier.verify(request.credential())).thenReturn(null);
          } catch (Exception e) {}
        }
      ),
      new Case("Getting account had an error", new GoogleAuthenticationRequest(
          "creds",
          "csrf",
          "csrf"
        ), Either.left("Get account error"), request -> {
          var token = mock(GoogleIdToken.class);
          var payload = mock(Payload.class);
          var accountId = "accountId";
          when(payload.getSubject()).thenReturn(accountId);
          when(token.getPayload()).thenReturn(payload);
          when(accountService.getForGoogleAccountId(accountId)).thenReturn(
            Either.left("Get account error")
          );
          try {
            when(verifier.verify(request.credential())).thenReturn(token);
          } catch (Exception e) {}
        }),
      new Case("Created happy path session", new GoogleAuthenticationRequest(
          "creds",
          "csrf",
          "csrf"
        ), Either.right(new Session(new UUID(0, 0), "accountId")), request -> {
          var token = mock(GoogleIdToken.class);
          var payload = mock(Payload.class);
          var accountId = "accountId";
          var expectedAccount = new Account(1L, 1L, accountId);
          var expectedSession = new Session(new UUID(0, 0), accountId);
          when(payload.getSubject()).thenReturn(accountId);
          when(token.getPayload()).thenReturn(payload);
          when(accountService.getForGoogleAccountId(accountId)).thenReturn(
            Either.right(expectedAccount)
          );
          when(sessionService.create(expectedAccount)).thenReturn(
            expectedSession
          );
          try {
            when(verifier.verify(request.credential())).thenReturn(token);
          } catch (Exception e) {}
        }),
    };
    for (var test : tests) {
      if (test.mock() != null) {
        test.mock().accept(test.request());
      }
      assertEquals(
        test.expected(),
        googleAuthenticationStrategy.authenticate(test.request())
      );
    }
  }
}
