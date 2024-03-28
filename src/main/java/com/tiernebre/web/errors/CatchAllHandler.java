package com.tiernebre.web.errors;

import com.tiernebre.web.util.WebHelper;
import io.javalin.http.Context;
import io.javalin.http.ExceptionHandler;
import io.jstach.jstachio.JStachio;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CatchAllHandler implements ExceptionHandler<Exception> {

  private final WebHelper helper;

  private static final Logger LOG = LoggerFactory.getLogger(
    CatchAllHandler.class
  );

  public CatchAllHandler(WebHelper helper) {
    this.helper = helper;
  }

  @Override
  public void handle(@NotNull Exception exception, @NotNull Context ctx) {
    LOG.error("Caught unhandled and unmapped exception {}", exception);
    var template = new com.tiernebre.web.templates.Error(
      "An unexpected error happened on our end."
    );
    try {
      helper.template(ctx, template);
    } catch (Exception e) {
      ctx.html(JStachio.render(template));
    }
  }
}
