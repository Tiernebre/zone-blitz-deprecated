package com.tiernebre.web;

import com.tiernebre.authentication.AuthenticationConstants;
import com.tiernebre.authentication.AuthenticationContextFactory;
import com.tiernebre.database.DatabaseConnectionError;
import com.tiernebre.database.DatabaseConnectionFactory;
import com.tiernebre.database.DatabaseConstants;
import com.tiernebre.database.JooqDslContextFactory;
import com.tiernebre.web.routes.RoutesFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;

public final class ServerFactory {

  public Server create()
    throws GeneralSecurityException, IOException, DatabaseConnectionError {
    var authenticationContextFactory = new AuthenticationContextFactory(
      AuthenticationConstants.CONFIGURATION,
      new JooqDslContextFactory(
        new DatabaseConnectionFactory(DatabaseConstants.CONFIGURATION)
      )
    );
    return new Server(
      new RoutesFactory(authenticationContextFactory.create()).create()
    );
  }
}
