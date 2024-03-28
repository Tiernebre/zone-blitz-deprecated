package com.tiernebre.web.errors;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

public final class Errors {

  private final CatchAllHandler catchAllHandler;
  private final NotFoundHandler notFoundHandler;
  private final UnauthenticatedForbiddenHandler unauthenticatedForbiddenHandler;

  public Errors(
    CatchAllHandler catchAllHandler,
    NotFoundHandler notFoundHandler,
    UnauthenticatedForbiddenHandler unauthenticatedForbiddenHandler
  ) {
    this.catchAllHandler = catchAllHandler;
    this.notFoundHandler = notFoundHandler;
    this.unauthenticatedForbiddenHandler = unauthenticatedForbiddenHandler;
  }

  public Javalin register(Javalin app) {
    return app
      .exception(Exception.class, catchAllHandler)
      .error(HttpStatus.NOT_FOUND, notFoundHandler)
      .error(HttpStatus.UNAUTHORIZED, unauthenticatedForbiddenHandler)
      .error(HttpStatus.FORBIDDEN, unauthenticatedForbiddenHandler);
  }
}
