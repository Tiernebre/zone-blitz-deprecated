package com.tiernebre.web.guards;

import io.javalin.Javalin;

public final class Guards {

  private final AuthenticationGuard authenticationGuard;

  public Guards(AuthenticationGuard authenticationGuard) {
    this.authenticationGuard = authenticationGuard;
  }

  public Javalin register(Javalin app) {
    return app.beforeMatched(authenticationGuard);
  }
}
