package com.tiernebre.authentication.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
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

    GoogleIdToken token;
    try {
      token = verifier.verify(request.credential());
    } catch (GeneralSecurityException | IOException e) {
      throw new InvalidGoogleSignOnAttemptException(
        "Token provided in Google sign on request was not able to be parsed."
      );
    }

    if (token != null) {
      LOG.debug(
        "Successfully processed and verified Google sign on token that was valid."
      );
      var payload = token.getPayload();
      var userId = payload.getSubject();
    } else {
      LOG.debug("Token provided was not parsed.");
    }
  }
}
