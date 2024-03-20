package com.tiernebre.authentication.google;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.tiernebre.authentication.account.Account;
import com.tiernebre.authentication.account.AccountService;
import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionService;
import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.error.ZoneBlitzServerError;
import io.vavr.collection.List;
import io.vavr.control.Either;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.UUID;
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

  @Test
  public void authenticate() throws GeneralSecurityException, IOException {
    TestCaseRunner.run(
      GoogleAuthenticationStrategyTest.class,
      List.<
        TestCase<GoogleAuthenticationRequest, Either<ZoneBlitzError, Session>>
      >of(
        new TestCase<
          GoogleAuthenticationRequest,
          Either<ZoneBlitzError, Session>
        >(
          "Invalid request",
          new GoogleAuthenticationRequest(null, null, null),
          __ -> Either.left(new ZoneBlitzServerError("Invalid request")),
          (request, __) -> {
            when(validator.parseCredential(request)).thenReturn(
              Either.left(new ZoneBlitzServerError("Invalid request"))
            );
          }
        ),
        new TestCase<
          GoogleAuthenticationRequest,
          Either<ZoneBlitzError, Session>
        >(
          "Token verifier returns null",
          new GoogleAuthenticationRequest("creds", "csrf", "csrf"),
          __ ->
            Either.left(
              new ZoneBlitzServerError(
                "Could not verify and parse given Google authentication credential."
              )
            ),
          (request, __) -> {
            when(validator.parseCredential(request)).thenReturn(
              Either.right(request.credential())
            );
            try {
              when(verifier.verify(request.credential())).thenReturn(null);
            } catch (Exception e) {}
          }
        ),
        new TestCase<
          GoogleAuthenticationRequest,
          Either<ZoneBlitzError, Session>
        >(
          "Getting account had an error",
          new GoogleAuthenticationRequest("creds", "csrf", "csrf"),
          __ -> Either.left(new ZoneBlitzServerError("Get account error")),
          (request, __) -> {
            when(validator.parseCredential(request)).thenReturn(
              Either.right(request.credential())
            );
            var token = mock(GoogleIdToken.class);
            var payload = mock(Payload.class);
            var accountId = "accountId";
            when(payload.getSubject()).thenReturn(accountId);
            when(token.getPayload()).thenReturn(payload);
            when(accountService.getForGoogleAccount(accountId)).thenReturn(
              Either.left(new ZoneBlitzServerError("Get account error"))
            );
            try {
              when(verifier.verify(request.credential())).thenReturn(token);
            } catch (Exception e) {}
          }
        ),
        new TestCase<
          GoogleAuthenticationRequest,
          Either<ZoneBlitzError, Session>
        >(
          "Happy path valid request and created session",
          new GoogleAuthenticationRequest("creds", "csrf", "csrf"),
          __ ->
            Either.right(new Session(new UUID(0, 0), 1L, LocalDateTime.now())),
          (request, expected) -> {
            when(validator.parseCredential(request)).thenReturn(
              Either.right(request.credential())
            );
            var token = mock(GoogleIdToken.class);
            var payload = mock(Payload.class);
            var accountId = "accountId";
            var expectedAccount = new Account(1L, 1L, accountId);
            when(payload.getSubject()).thenReturn(accountId);
            when(token.getPayload()).thenReturn(payload);
            when(accountService.getForGoogleAccount(accountId)).thenReturn(
              Either.right(expectedAccount)
            );
            when(sessionService.create(expectedAccount)).thenReturn(
              expected.get()
            );
            try {
              when(verifier.verify(request.credential())).thenReturn(token);
            } catch (Exception e) {}
          }
        )
      ),
      googleAuthenticationStrategy::authenticate
    );
  }
}
