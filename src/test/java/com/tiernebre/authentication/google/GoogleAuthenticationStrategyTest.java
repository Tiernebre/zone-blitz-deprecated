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
import io.vavr.collection.Stream;
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

  private final record FailureTestCase(
    String name,
    GoogleAuthenticationRequest request,
    String expectedError
  ) {}

  @Test
  public void failureCases() {
    var tests = new FailureTestCase[] {
      new FailureTestCase("Null request", null, "Request received was null."),
      new FailureTestCase(
        "No Body CSRF Token",
        new GoogleAuthenticationRequest("creds", null, "csrf"),
        "Request has invalid CSRF tokens."
      ),
      new FailureTestCase(
        "No Cookie CSRF Token",
        new GoogleAuthenticationRequest("creds", "csrf", null),
        "Request has invalid CSRF tokens."
      ),
      new FailureTestCase(
        "No Body and Cookie CSRF Token",
        new GoogleAuthenticationRequest("creds", null, null),
        "Request has invalid CSRF tokens."
      ),
      new FailureTestCase(
        "Body and Cookie CSRF Tokens are not equal",
        new GoogleAuthenticationRequest("creds", "body", "cookie"),
        "Request has invalid CSRF tokens."
      ),
    };
    Stream.of(tests).forEach(test -> {
      System.out.println(test.name);
      var result = googleAuthenticationStrategy.authenticate(test.request);
      assertTrue(result.isEmpty());
      assertEquals(test.expectedError, result.getLeft());
    });
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
