package com.tiernebre.web.middlewares;

import io.javalin.Javalin;

public final class Middlewares {

  private final SessionMiddleware authenticationMiddleware;

  public Middlewares(SessionMiddleware authenticationMiddleware) {
    this.authenticationMiddleware = authenticationMiddleware;
  }

  public Javalin register(Javalin app) {
    return app.before(authenticationMiddleware);
  }
}
