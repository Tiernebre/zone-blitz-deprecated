package com.tiernebre.web.middlewares;

import com.tiernebre.web.util.SessionRegistry;
import com.tiernebre.web.util.WebHelper;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public final class SessionRefresherMiddleware implements Handler {

  private final WebHelper helper;
  private final SessionRegistry registry;

  public SessionRefresherMiddleware(
    WebHelper helper,
    SessionRegistry registry
  ) {
    this.helper = helper;
    this.registry = registry;
  }

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    helper.session(ctx).peek(session -> registry.refresh(ctx, session));
  }
}
