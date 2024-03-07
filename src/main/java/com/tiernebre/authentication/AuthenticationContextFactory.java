package com.tiernebre.authentication;

import com.tiernebre.authentication.account.DefaultAccountService;
import com.tiernebre.authentication.account.JooqAccountRepository;
import com.tiernebre.authentication.google.GoogleAuthenticationStrategy;
import com.tiernebre.authentication.google.GoogleIdTokenVerifierFactory;
import com.tiernebre.authentication.registration.DefaultRegistrationService;
import com.tiernebre.authentication.registration.JooqRegistrationRepository;
import com.tiernebre.authentication.registration.VavrRegistrationValidator;
import com.tiernebre.authentication.session.DefaultSessionService;
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
    var dsl = databaseContext.dslContext();
    var sessionService = new DefaultSessionService(
      new JooqSessionRepository(dsl)
    );
    return new AuthenticationContext(
      AuthenticationConstants.CONFIGURATION,
      new GoogleAuthenticationStrategy(
        new GoogleIdTokenVerifierFactory(
          AuthenticationConstants.CONFIGURATION.oauthGoogleClientId()
        ).create(),
        sessionService,
        new DefaultAccountService(new JooqAccountRepository(dsl))
      ),
      sessionService,
      new DefaultRegistrationService(new JooqRegistrationRepository(dsl)),
      new VavrRegistrationValidator()
    );
  }
}
