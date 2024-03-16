package com.tiernebre.web.controllers;

import com.tiernebre.web.constants.WebConstants;
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
    JStachio.render(
      new Login(
        googleClientId,
        String.format("%s/api/authenticate", WebConstants.URL)
      ),
      output
    );
    ctx.header(
      WebConstants.CONTENT_SECURITY_POLICY_HEADER_NAME,
      String.format(
        "%s https://accounts.google.com; style-src 'self' https://accounts.google.com 'unsafe-inline'",
        WebConstants.CONTENT_SECURITY_POLICY
      )
    );
    ctx.html(output.toString());
  }
}
