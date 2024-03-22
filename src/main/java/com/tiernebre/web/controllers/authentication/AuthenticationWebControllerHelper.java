package com.tiernebre.web.controllers.authentication;

import com.tiernebre.web.constants.WebConstants;
import io.javalin.http.Context;

public final class AuthenticationWebControllerHelper {

  public static void allowGoogleScript(Context ctx) {
    ctx.header(
      WebConstants.CONTENT_SECURITY_POLICY_HEADER_NAME,
      String.format(
        "%1$s %2$s; style-src 'self' %2$s 'unsafe-inline'",
        WebConstants.CONTENT_SECURITY_POLICY,
        AuthenticationWebConstants.GOOGLE_ACCOUNTS_URL
      )
    );
  }
}
