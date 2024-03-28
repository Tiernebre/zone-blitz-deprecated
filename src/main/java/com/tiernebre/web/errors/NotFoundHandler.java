package com.tiernebre.web.errors;

import com.tiernebre.web.util.WebHelper;
import io.javalin.http.Context;
import io.javalin.http.ExceptionHandler;
import io.javalin.http.HttpStatus;
import io.javalin.http.NotFoundResponse;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NotFoundHandler
  implements ExceptionHandler<NotFoundResponse> {

  private final WebHelper helper;

  private static final Logger LOG = LoggerFactory.getLogger(
    NotFoundHandler.class
  );

  public NotFoundHandler(WebHelper helper) {
    this.helper = helper;
  }

  @Override
  public void handle(
    @NotNull NotFoundResponse exception,
    @NotNull Context ctx
  ) {
    LOG.debug("Requested route {} was not found.", ctx.path());
    helper.template(
      ctx,
      new com.tiernebre.web.templates.Error(
        "The requested page could not be found."
      )
    );
    ctx.status(HttpStatus.NOT_FOUND);
  }
}
