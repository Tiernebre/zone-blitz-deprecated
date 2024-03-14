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
import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.error.ZoneBlitzServerError;
import io.vavr.collection.List;
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
        )
      ),
      googleAuthenticationStrategy::authenticate
    );
  }
}
