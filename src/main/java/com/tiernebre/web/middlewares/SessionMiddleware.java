package com.tiernebre.web.middlewares;

import com.tiernebre.authentication.session.SessionService;
import com.tiernebre.web.constants.WebConstants;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.vavr.control.Option;
import io.vavr.control.Try;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public final class SessionMiddleware implements Handler {

  private final SessionService sessionService;

  public SessionMiddleware(SessionService sessionService) {
    this.sessionService = sessionService;
  }

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    Option.of(ctx.cookie(WebConstants.SESSION_COOKIE_TOKEN_NAME))
      .flatMap(token -> Try.of(() -> UUID.fromString(token)).toOption())
      .flatMap(sessionService::get)
      .peek(session -> {
        ctx.attribute(WebConstants.JAVALIN_SESSION_ATTRIBUTE, session);
      });
  }
}
