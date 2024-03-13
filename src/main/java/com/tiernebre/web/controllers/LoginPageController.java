package com.tiernebre.web.controllers;

import com.tiernebre.web.templates.Login;
import io.javalin.http.Context;
import io.jstach.jstachio.JStachio;

public final class LoginPageController {

  private final String googleClientId;

  public LoginPageController(String googleClientId) {
    this.googleClientId = googleClientId;
  }

  public void render(Context ctx) {
    var output = new StringBuilder();
    JStachio.render(new Login(googleClientId), output);
    ctx.html(output.toString());
  }
}
