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
        String.format("%s/api/authenticate", WebConstants.URL),
        String.format(
          "%s/gsi/client",
          WebConstants.Authentication.GOOGLE_ACCOUNTS_URL
        )
      ),
      output
    );
    ctx.html(output.toString());
    ctx.header(
      WebConstants.CONTENT_SECURITY_POLICY_HEADER_NAME,
      String.format(
        "%1$s %2$s; style-src 'self' %2$s 'unsafe-inline'",
        WebConstants.CONTENT_SECURITY_POLICY,
        WebConstants.Authentication.GOOGLE_ACCOUNTS_URL
      )
    );
  }
}
