package com.tiernebre.authentication.google;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.tiernebre.authentication.session.SessionRepository;
import org.junit.Test;

public final class GoogleAuthenticationStrategyTest {

  private final GoogleIdTokenVerifier verifier = mock(
    GoogleIdTokenVerifier.class
  );
  private final SessionRepository repository = mock(SessionRepository.class);
  private final GoogleAuthenticationStrategy googleAuthenticationStrategy =
    new GoogleAuthenticationStrategy(verifier, repository);

  @Test
  public void returnsEmptyIfNoRequest() {
    assertTrue(googleAuthenticationStrategy.authenticate(null).isEmpty());
  }

  @Test
  public void returnsEmptyIfNoBodyCsrfToken() {
    assertTrue(
      googleAuthenticationStrategy
        .authenticate(
          new GoogleAuthenticationRequest("creds", null, "cookieCrsfToken")
        )
        .isEmpty()
    );
  }

  @Test
  public void returnsEmptyIfNoCookieCsrfToken() {
    assertTrue(
      googleAuthenticationStrategy
        .authenticate(
          new GoogleAuthenticationRequest("creds", "bodyCsrfToken", null)
        )
        .isEmpty()
    );
  }
}
