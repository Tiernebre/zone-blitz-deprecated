package com.tiernebre.context;

import com.tiernebre.authentication.AuthenticationContextFactory;
import com.tiernebre.database.DatabaseConnectionError;
import com.tiernebre.database.DatabaseContextFactory;
import com.tiernebre.web.WebContextFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;

public final class DependencyContextFactory {

  public DependencyContext create()
    throws DatabaseConnectionError, GeneralSecurityException, IOException {
    var utilityContext = new UtilityContextFactory().create();
    var databaseContext = new DatabaseContextFactory().create();
    var authenticationContext = new AuthenticationContextFactory(
      databaseContext
    ).create();
    return new DependencyContext(
      databaseContext,
      authenticationContext,
      new WebContextFactory(authenticationContext, utilityContext).create()
    );
  }
}
