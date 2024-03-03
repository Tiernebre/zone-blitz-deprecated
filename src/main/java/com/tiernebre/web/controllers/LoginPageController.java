package com.tiernebre.web.controllers;

import com.tiernebre.web.templates.LoginPage;
import io.javalin.http.Context;
import io.jstach.jstachio.JStachio;

public class LoginPageController {

  public void render(Context ctx) {
    var output = new StringBuilder();
    JStachio.render(
      new LoginPage(
        System.getenv("ZONE_BLITZ_OAUTH_GOOGLE_CLIENT_ID"),
        "https://dev.zoneblitz.app"
      ),
      output
    );
    ctx.html(output.toString());
  }
}
