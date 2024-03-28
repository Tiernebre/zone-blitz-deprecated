package com.tiernebre.web.errors;

import com.tiernebre.util.error.ZoneBlitzClientError;
import com.tiernebre.web.util.WebHelper;
import io.javalin.http.Context;
import io.javalin.http.ExceptionHandler;
import org.jetbrains.annotations.NotNull;

public final class ZoneBlitzClientErrorHandler
  implements ExceptionHandler<ZoneBlitzClientError> {

  private final WebHelper helper;

  public ZoneBlitzClientErrorHandler(WebHelper helper) {
    this.helper = helper;
  }

  @Override
  public void handle(
    @NotNull ZoneBlitzClientError exception,
    @NotNull Context ctx
  ) {}
}
