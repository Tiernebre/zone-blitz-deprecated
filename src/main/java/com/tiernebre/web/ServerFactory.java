package com.tiernebre.web;

import com.tiernebre.authentication.AuthenticationContextFactory;
import com.tiernebre.database.DatabaseConnectionError;
import com.tiernebre.database.DatabaseContextFactory;
import com.tiernebre.web.routes.RoutesFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;

public final class ServerFactory {

  public Server create()
    throws GeneralSecurityException, IOException, DatabaseConnectionError {
    return new Server(
      new RoutesFactory(
        new AuthenticationContextFactory(
          new DatabaseContextFactory().create()
        ).create()
      ).create()
    );
  }
}
