package com.tiernebre.authentication.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GoogleAuthenticationService {

  private static final Logger LOG = LoggerFactory.getLogger(
    GoogleAuthenticationService.class
  );

  private final GoogleIdTokenVerifier verifier;

  public GoogleAuthenticationService(GoogleIdTokenVerifier verifier) {
    this.verifier = verifier;
  }

  public void login(GoogleAuthenticationRequest request)
    throws InvalidGoogleSignOnAttemptException {
    var bodyCrsfToken = request.bodyCrsfToken();
    var cookieCsrfToken = request.cookieCsrfToken();

    if (
      bodyCrsfToken == null ||
      cookieCsrfToken == null ||
      !bodyCrsfToken.equals(cookieCsrfToken)
    ) {
      throw new InvalidGoogleSignOnAttemptException(
        "No or invalid CSRF token provided."
      );
    }

    try {
      var token = verifier.verify(request.credential());

      if (token != null) {
        var payload = token.getPayload();
        var userId = payload.getSubject();
      }
    } catch (GeneralSecurityException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
