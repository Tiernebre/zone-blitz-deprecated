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
  private final GoogleAuthenticationValidator validator = mock(
    GoogleAuthenticationValidator.class
  );
  private final GoogleAuthenticationStrategy googleAuthenticationStrategy =
    new GoogleAuthenticationStrategy(
      verifier,
      sessionService,
      accountService,
      validator
    );

  private final record Case(
    String name,
    GoogleAuthenticationRequest request,
    Either<String, Session> expected,
    Consumer<GoogleAuthenticationRequest> mock
  ) {}

  @Test
  public void cases() throws GeneralSecurityException, IOException {
    var cases = new Case[] {
      new Case("Invalid request", new GoogleAuthenticationRequest(
          "creds",
          "csrf",
          "csrf"
        ), Either.left("Invalid request"), request -> {
          when(validator.parseCredential(request)).thenReturn(
            Either.left("Invalid request")
          );
        }),
      new Case(
        "Google token verifier returns null",
        new GoogleAuthenticationRequest("creds", "csrf", "csrf"),
        Either.left(
          "Could not verify and parse given Google authentication credential."
        ),
        request -> {
          when(validator.parseCredential(request)).thenReturn(
            Either.right(request.credential())
          );
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
          when(validator.parseCredential(request)).thenReturn(
            Either.right(request.credential())
          );
          var token = mock(GoogleIdToken.class);
          var payload = mock(Payload.class);
          var accountId = "accountId";
          when(payload.getSubject()).thenReturn(accountId);
          when(token.getPayload()).thenReturn(payload);
          when(accountService.getForGoogleAccount(accountId)).thenReturn(
            Either.left("Get account error")
          );
          try {
            when(verifier.verify(request.credential())).thenReturn(token);
          } catch (Exception e) {}
        }),
      new Case(
        "Happy path valid request and created session",
        new GoogleAuthenticationRequest("creds", "csrf", "csrf"),
        Either.right(new Session(new UUID(0, 0), 1L)),
        request -> {
          when(validator.parseCredential(request)).thenReturn(
            Either.right(request.credential())
          );
          var token = mock(GoogleIdToken.class);
          var payload = mock(Payload.class);
          var accountId = "accountId";
          var expectedAccount = new Account(1L, 1L, accountId);
          var expectedSession = new Session(
            new UUID(0, 0),
            expectedAccount.id()
          );
          when(payload.getSubject()).thenReturn(accountId);
          when(token.getPayload()).thenReturn(payload);
          when(accountService.getForGoogleAccount(accountId)).thenReturn(
            Either.right(expectedAccount)
          );
          when(sessionService.create(expectedAccount)).thenReturn(
            expectedSession
          );
          try {
            when(verifier.verify(request.credential())).thenReturn(token);
          } catch (Exception e) {}
        }
      ),
    };
    for (var test : cases) {
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
