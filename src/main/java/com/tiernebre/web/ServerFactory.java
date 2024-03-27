package com.tiernebre.web;

import com.tiernebre.context.DependencyContext;
import com.tiernebre.database.DatabaseConnectionError;
import com.tiernebre.web.errors.ErrorsFactory;
import com.tiernebre.web.guards.GuardsFactory;
import com.tiernebre.web.middlewares.MiddlewaresFactory;
import com.tiernebre.web.routes.RoutesFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;

public final class ServerFactory {

  private final DependencyContext dependencyContext;

  public ServerFactory(DependencyContext dependencyContext) {
    this.dependencyContext = dependencyContext;
  }

  public Server create()
    throws GeneralSecurityException, IOException, DatabaseConnectionError {
    return new Server(
      new RoutesFactory(dependencyContext).create(),
      new MiddlewaresFactory(dependencyContext).create(),
      new GuardsFactory(dependencyContext).create(),
      new ErrorsFactory().create()
    );
  }
}
