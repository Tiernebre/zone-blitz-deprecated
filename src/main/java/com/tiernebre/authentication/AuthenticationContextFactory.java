package com.tiernebre.authentication;

import com.tiernebre.authentication.google.GoogleAuthenticationStrategy;
import com.tiernebre.authentication.google.GoogleIdTokenVerifierFactory;
import com.tiernebre.authentication.session.JooqSessionRepository;
import com.tiernebre.database.DatabaseConnectionError;
import com.tiernebre.database.DatabaseContext;
import java.io.IOException;
import java.security.GeneralSecurityException;

public final class AuthenticationContextFactory {

  private final DatabaseContext databaseContext;

  public AuthenticationContextFactory(DatabaseContext databaseContext) {
    this.databaseContext = databaseContext;
  }

  public AuthenticationContext create()
    throws GeneralSecurityException, IOException, DatabaseConnectionError {
    return new AuthenticationContext(
      AuthenticationConstants.CONFIGURATION,
      new GoogleAuthenticationStrategy(
        new GoogleIdTokenVerifierFactory(
          AuthenticationConstants.CONFIGURATION.oauthGoogleClientId()
        ).create(),
        new JooqSessionRepository(databaseContext.dslContext())
      )
    );
  }
}
