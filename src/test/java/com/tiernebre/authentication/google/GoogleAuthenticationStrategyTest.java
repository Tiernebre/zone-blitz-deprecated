package com.tiernebre.authentication.google;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionRepository;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.UUID;
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

  @Test
  public void returnsEmptyIfCsrfTokensDoNotMatch() {
    assertTrue(
      googleAuthenticationStrategy
        .authenticate(
          new GoogleAuthenticationRequest(
            "creds",
            "bodyCsrfToken",
            "cookieCsrfToken"
          )
        )
        .isEmpty()
    );
  }

  @Test
  public void returnsEmptyIfTokenVerifierThrew()
    throws GeneralSecurityException, IOException {
    var request = new GoogleAuthenticationRequest(
      "creds",
      "csrfToken",
      "csrfToken"
    );
    when(verifier.verify(request.credential())).thenThrow(new IOException());
    assertTrue(googleAuthenticationStrategy.authenticate(request).isEmpty());
  }

  @Test
  public void returnsEmptyIfTokenVerifierReturnedNull()
    throws GeneralSecurityException, IOException {
    var request = new GoogleAuthenticationRequest(
      "creds",
      "csrfToken",
      "csrfToken"
    );
    when(verifier.verify(request.credential())).thenReturn(null);
    assertTrue(googleAuthenticationStrategy.authenticate(request).isEmpty());
  }

  @Test
  public void returnsSession() throws GeneralSecurityException, IOException {
    var request = new GoogleAuthenticationRequest(
      "creds",
      "csrfToken",
      "csrfToken"
    );
    var token = mock(GoogleIdToken.class);
    var payload = mock(Payload.class);
    String accountId = UUID.randomUUID().toString();
    when(payload.getSubject()).thenReturn(accountId);
    when(token.getPayload()).thenReturn(payload);
    when(verifier.verify(request.credential())).thenReturn(token);
    var expectedSession = new Session(UUID.randomUUID(), accountId);
    when(repository.insertOne(accountId)).thenReturn(expectedSession);
    var session = googleAuthenticationStrategy.authenticate(request);
    assertTrue(session.isRight());
    assertEquals(expectedSession, session.get());
  }
}
