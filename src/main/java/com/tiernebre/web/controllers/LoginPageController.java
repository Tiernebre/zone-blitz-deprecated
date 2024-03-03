package com.tiernebre.web.controllers;

import com.tiernebre.web.templates.LoginPage;
import io.javalin.http.Context;
import io.jstach.jstachio.JStachio;

public class LoginPageController {

  public void render(Context ctx) {
    var output = new StringBuilder();
    JStachio.render(new LoginPage(), output);
    ctx.html(output.toString());
  }
}
