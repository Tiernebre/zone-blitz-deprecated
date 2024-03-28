package com.tiernebre.web.errors;

import com.tiernebre.web.util.WebHelper;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UnauthenticatedForbiddenHandler implements Handler {

  private final WebHelper helper;

  private static final Logger LOG = LoggerFactory.getLogger(
    NotFoundHandler.class
  );

  public UnauthenticatedForbiddenHandler(WebHelper helper) {
    this.helper = helper;
  }

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    LOG.debug(
      "A user attempted to perform an action that they were unauthenticated or forbidden to do."
    );
    helper.template(
      ctx,
      new com.tiernebre.web.templates.Error(
        "You are not allowed to access this page or perform the requested action."
      )
    );
    ctx.status(HttpStatus.UNAUTHORIZED);
  }
}
