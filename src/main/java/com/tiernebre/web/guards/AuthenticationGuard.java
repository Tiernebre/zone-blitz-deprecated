package com.tiernebre.web.guards;

import com.tiernebre.web.constants.WebUserRole;
import com.tiernebre.web.util.WebHelper;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import org.jetbrains.annotations.NotNull;

public final class AuthenticationGuard implements Handler {

  private final WebHelper helper;

  public AuthenticationGuard(WebHelper helper) {
    this.helper = helper;
  }

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    helper
      .session(ctx)
      .onEmpty(() -> {
        if (ctx.routeRoles().contains(WebUserRole.LOGGED_IN)) {
          throw new UnauthorizedResponse(
            "You must be logged in to view and interact with this page."
          );
        }
      });
  }
}
