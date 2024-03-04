package com.tiernebre.web.controllers.authentication;

import com.tiernebre.web.controllers.GoogleAuthenticationController;
import io.javalin.http.Context;

public final class ProxyAuthenticationController
  implements AuthenticationController {

  private final GoogleAuthenticationController googleController;

  public ProxyAuthenticationController(
    GoogleAuthenticationController googleController
  ) {
    this.googleController = googleController;
  }

  public void handle(Context context) {
    googleController.handle(context);
  }
}
