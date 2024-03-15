package com.tiernebre.web.middlewares;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class SecurityMiddleware implements Handler {

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    ctx.header(
      "Content-Security-Policy",
      "default-src 'self' https://accounts.google.com/gsi/client"
    );
  }
}
