package com.tiernebre.web.middlewares;

import com.tiernebre.web.util.SessionRegistry;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public final class SessionMiddleware implements Handler {

  private final SessionRegistry registry;

  public SessionMiddleware(SessionRegistry registry) {
    this.registry = registry;
  }

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    registry.parse(ctx);
  }
}
