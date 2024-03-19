package com.tiernebre.web.controllers.authentication;

import com.tiernebre.web.controllers.ControllerHelper;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

public final class LogoutController implements Handler {

  private final SessionRegister sessionRegister;
  private final ControllerHelper helper;

  public LogoutController(
    SessionRegister sessionRegister,
    ControllerHelper helper
  ) {
    this.sessionRegister = sessionRegister;
    this.helper = helper;
  }

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    helper.session(ctx).peek(session -> sessionRegister.delete(ctx, session));
    ctx.status(HttpStatus.OK);
    ctx.redirect("/");
  }
}
