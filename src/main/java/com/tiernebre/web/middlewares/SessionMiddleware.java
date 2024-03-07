package com.tiernebre.web.middlewares;

import com.tiernebre.authentication.session.SessionService;
import com.tiernebre.web.WebConstants;
import com.tiernebre.web.controllers.authentication.WebAuthenticationConstants;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.vavr.control.Option;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public final class SessionMiddleware implements Handler {

  private final SessionService sessionService;

  public SessionMiddleware(SessionService sessionService) {
    this.sessionService = sessionService;
  }

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    Option.of(ctx.cookie(WebAuthenticationConstants.SESSION_COOKIE_TOKEN_NAME))
      .map(UUID::fromString)
      .flatMap(sessionService::get)
      .peek(session -> {
        ctx.attribute(WebConstants.JAVALIN_SESSION_ATTRIBUTE, session);
      });
  }
}
