package com.tiernebre.authentication;

import com.tiernebre.authentication.google.GoogleAuthenticationService;
import com.tiernebre.authentication.google.GoogleIdTokenVerifierFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;

public final class AuthenticationContextFactory {

  private final AuthenticationConfiguration configuration;

  public AuthenticationContextFactory(
    AuthenticationConfiguration configuration
  ) {
    this.configuration = configuration;
  }

  public AuthenticationContext create()
    throws GeneralSecurityException, IOException {
    return new AuthenticationContext(
      configuration,
      new GoogleAuthenticationService(
        new GoogleIdTokenVerifierFactory(
          configuration.oauthGoogleClientId()
        ).create()
      )
    );
  }
}
