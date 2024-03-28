package com.tiernebre.web.errors;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

public final class Errors {

  private final CatchAllHandler catchAllHandler;
  private final NotFoundHandler notFoundHandler;

  public Errors(
    CatchAllHandler catchAllHandler,
    NotFoundHandler notFoundHandler
  ) {
    this.catchAllHandler = catchAllHandler;
    this.notFoundHandler = notFoundHandler;
  }

  public Javalin register(Javalin app) {
    return app
      .exception(Exception.class, catchAllHandler)
      .error(HttpStatus.NOT_FOUND, notFoundHandler);
  }
}
