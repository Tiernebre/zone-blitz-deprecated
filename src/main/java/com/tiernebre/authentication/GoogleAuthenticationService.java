package com.tiernebre.authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import java.io.IOException;
import java.security.GeneralSecurityException;

public final class GoogleAuthenticationService {

  private final GoogleIdTokenVerifier verifier;

  public GoogleAuthenticationService(GoogleIdTokenVerifier verifier) {
    this.verifier = verifier;
  }

  public void login(GoogleAuthenticationRequest request) {
    var bodyCrsfToken = request.bodyCrsfToken();
    var cookieCsrfToken = request.cookieCsrfToken();

    if (
      bodyCrsfToken == null ||
      cookieCsrfToken == null ||
      !bodyCrsfToken.equals(cookieCsrfToken)
    ) {
      throw new RuntimeException("No or invalid CSRF token provided.");
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
