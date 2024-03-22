package com.tiernebre.web.middlewares;

import io.javalin.Javalin;

public final class Middlewares {

  private final SessionParserMiddleware sessionParserMiddleware;
  private final SecurityMiddleware securityMiddleware;
  private final SessionRefresherMiddleware sessionRefresherMiddleware;

  public Middlewares(
    SessionParserMiddleware sessionParserMiddleware,
    SecurityMiddleware securityMiddleware,
    SessionRefresherMiddleware sessionRefresherMiddleware
  ) {
    this.sessionParserMiddleware = sessionParserMiddleware;
    this.securityMiddleware = securityMiddleware;
    this.sessionRefresherMiddleware = sessionRefresherMiddleware;
  }

  public Javalin register(Javalin app) {
    return app
      .before(sessionParserMiddleware)
      .after(securityMiddleware)
      .after(sessionRefresherMiddleware);
  }
}
