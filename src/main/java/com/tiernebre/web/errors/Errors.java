package com.tiernebre.web.errors;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.UnauthorizedResponse;

public final class Errors {

  private final CatchAllHandler catchAllHandler;
  private final NotFoundHandler notFoundHandler;
  private final UnauthenticatedHandler unauthenticatedHandler;

  public Errors(
    CatchAllHandler catchAllHandler,
    NotFoundHandler notFoundHandler,
    UnauthenticatedHandler unauthenticatedHandler
  ) {
    this.catchAllHandler = catchAllHandler;
    this.notFoundHandler = notFoundHandler;
    this.unauthenticatedHandler = unauthenticatedHandler;
  }

  public Javalin register(Javalin app) {
    return app
      .exception(Exception.class, catchAllHandler)
      .exception(NotFoundResponse.class, notFoundHandler)
      .exception(UnauthorizedResponse.class, unauthenticatedHandler);
  }
}
