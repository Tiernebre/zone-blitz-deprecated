package com.tiernebre.web.middlewares;

import io.javalin.Javalin;

public final class Middlewares {

  private final SessionMiddleware sessionMiddleware;
  private final SecurityMiddleware securityMiddleware;

  public Middlewares(
    SessionMiddleware authenticationMiddleware,
    SecurityMiddleware securityMiddleware
  ) {
    this.sessionMiddleware = authenticationMiddleware;
    this.securityMiddleware = securityMiddleware;
  }

  public Javalin register(Javalin app) {
    return app.before(sessionMiddleware).after(securityMiddleware);
  }
}
