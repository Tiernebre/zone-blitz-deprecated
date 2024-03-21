package com.tiernebre.authentication;

import com.tiernebre.authentication.account.DefaultAccountService;
import com.tiernebre.authentication.account.JooqAccountRepository;
import com.tiernebre.authentication.google.GoogleAuthenticationStrategy;
import com.tiernebre.authentication.google.GoogleIdTokenVerifierFactory;
import com.tiernebre.authentication.google.VavrGoogleAuthenticationValidator;
import com.tiernebre.authentication.registration.DefaultRegistrationService;
import com.tiernebre.authentication.registration.JooqRegistrationRepository;
import com.tiernebre.authentication.registration.RegistrationAuthenticationStrategy;
import com.tiernebre.authentication.registration.VavrRegistrationValidator;
import com.tiernebre.authentication.session.DefaultSessionService;
import com.tiernebre.authentication.session.JooqSessionRepository;
import com.tiernebre.context.UtilityContext;
import com.tiernebre.database.DatabaseConnectionError;
import com.tiernebre.database.DatabaseContext;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public final class AuthenticationContextFactory {

  private final DatabaseContext databaseContext;
  private final UtilityContext utilityContext;

  public AuthenticationContextFactory(
    DatabaseContext databaseContext,
    UtilityContext utilityContext
  ) {
    this.databaseContext = databaseContext;
    this.utilityContext = utilityContext;
  }

  public AuthenticationContext create()
    throws GeneralSecurityException, IOException, DatabaseConnectionError {
    var dsl = databaseContext.dslContext();
    var sessionService = new DefaultSessionService(
      new JooqSessionRepository(dsl, utilityContext.clock())
    );
    var accountService = new DefaultAccountService(
      new JooqAccountRepository(dsl)
    );
    var registrationService = new DefaultRegistrationService(
      new JooqRegistrationRepository(dsl),
      new Argon2PasswordEncoder(16, 32, 1, 60000, 10),
      accountService,
      new VavrRegistrationValidator()
    );
    return new AuthenticationContext(
      AuthenticationConstants.CONFIGURATION,
      new GoogleAuthenticationStrategy(
        new GoogleIdTokenVerifierFactory(
          AuthenticationConstants.CONFIGURATION.oauthGoogleClientId()
        ).create(),
        sessionService,
        accountService,
        new VavrGoogleAuthenticationValidator()
      ),
      sessionService,
      registrationService,
      new RegistrationAuthenticationStrategy(
        registrationService,
        accountService,
        sessionService
      )
    );
  }
}
