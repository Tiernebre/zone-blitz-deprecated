package com.tiernebre.authentication;

import com.tiernebre.authentication.google.GoogleAuthenticationService;
import com.tiernebre.authentication.google.GoogleIdTokenVerifierFactory;
import com.tiernebre.authentication.session.JooqSessionRepository;
import com.tiernebre.database.DatabaseConnectionError;
import com.tiernebre.database.JooqDslContextFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;

public final class AuthenticationContextFactory {

  private final AuthenticationConfiguration configuration;
  private final JooqDslContextFactory jooqDslContextFactory;

  public AuthenticationContextFactory(
    AuthenticationConfiguration configuration,
    JooqDslContextFactory jooqDslContextFactory
  ) {
    this.configuration = configuration;
    this.jooqDslContextFactory = jooqDslContextFactory;
  }

  public AuthenticationContext create()
    throws GeneralSecurityException, IOException, DatabaseConnectionError {
    return new AuthenticationContext(
      configuration,
      new GoogleAuthenticationService(
        new GoogleIdTokenVerifierFactory(
          configuration.oauthGoogleClientId()
        ).create(),
        new JooqSessionRepository(jooqDslContextFactory.create())
      )
    );
  }
}
