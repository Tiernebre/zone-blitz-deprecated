package com.tiernebre.web.errors;

import io.javalin.http.Context;
import io.javalin.http.ExceptionHandler;
import io.jstach.jstachio.JStachio;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CatchAllHandler implements ExceptionHandler<Exception> {

  private static final Logger LOG = LoggerFactory.getLogger(
    CatchAllHandler.class
  );

  @Override
  public void handle(@NotNull Exception exception, @NotNull Context ctx) {
    LOG.error("Caught unhandled and unmapped exception {}", exception);
    ctx.html(
      JStachio.render(
        new com.tiernebre.web.templates.Error(
          "An unexpected error happened on our end."
        )
      )
    );
  }
}
