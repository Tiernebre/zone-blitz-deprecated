package com.tiernebre.web.middlewares;

import com.tiernebre.web.util.SessionRegistry;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public final class SessionParserMiddleware implements Handler {

  private final SessionRegistry registry;

  public SessionParserMiddleware(SessionRegistry registry) {
    this.registry = registry;
  }

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    registry.parse(ctx);
  }
}
