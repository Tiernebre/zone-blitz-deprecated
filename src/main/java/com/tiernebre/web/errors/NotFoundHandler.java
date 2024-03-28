package com.tiernebre.web.errors;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import io.jstach.jstachio.JStachio;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NotFoundHandler implements Handler {

  private static final Logger LOG = LoggerFactory.getLogger(
    NotFoundHandler.class
  );

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    LOG.debug("Requested route {} was not found.", ctx.path());
    ctx.html(
      JStachio.render(
        new com.tiernebre.web.templates.Error(
          "The requested page could not be found."
        )
      )
    );
    ctx.status(HttpStatus.NOT_FOUND);
  }
}
