package com.tiernebre.web.errors;

import io.javalin.http.Context;
import io.javalin.http.ExceptionHandler;
import io.javalin.http.UnauthorizedResponse;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UnauthenticatedHandler
  implements ExceptionHandler<UnauthorizedResponse> {

  private final Logger LOG = LoggerFactory.getLogger(
    UnauthenticatedHandler.class
  );

  @Override
  public void handle(
    @NotNull UnauthorizedResponse exception,
    @NotNull Context ctx
  ) {
    LOG.debug(
      "An attempt to navigate to a page or perform an action when logged out occurred."
    );
    ctx.redirect("/login");
  }
}
