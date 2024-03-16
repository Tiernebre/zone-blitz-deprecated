package com.tiernebre.web.controllers.authentication;

import com.tiernebre.web.constants.WebConstants;
import com.tiernebre.web.controllers.ControllerHelper;
import com.tiernebre.web.templates.Login;
import io.javalin.http.Context;
import java.io.IOException;

public final class LoginController {

  private final String googleClientId;
  private final ControllerHelper helper;

  public LoginController(String googleClientId, ControllerHelper helper) {
    this.googleClientId = googleClientId;
    this.helper = helper;
  }

  public void render(Context ctx) throws IOException {
    helper.renderHtml(
      ctx,
      new Login(
        googleClientId,
        String.format("%s/api/authenticate", WebConstants.URL),
        String.format("%s/gsi/client", Constants.GOOGLE_ACCOUNTS_URL),
        Constants.SHARED_AUTHENTICATION_FORM
      )
    );
    ctx.header(
      WebConstants.CONTENT_SECURITY_POLICY_HEADER_NAME,
      String.format(
        "%1$s %2$s; style-src 'self' %2$s 'unsafe-inline'",
        WebConstants.CONTENT_SECURITY_POLICY,
        Constants.GOOGLE_ACCOUNTS_URL
      )
    );
  }
}
