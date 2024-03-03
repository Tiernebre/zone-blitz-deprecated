package com.tiernebre.web;

import com.tiernebre.authentication.GoogleAuthenticationService;
import com.tiernebre.web.controllers.FrontPageController;
import com.tiernebre.web.controllers.GoogleAuthenticationController;
import com.tiernebre.web.controllers.HealthController;
import com.tiernebre.web.controllers.LoginPageController;

public final class RouterFactory {

  public Router create() {
    return new Router(
      new FrontPageController(),
      new HealthController(),
      new LoginPageController(),
      new GoogleAuthenticationController(new GoogleAuthenticationService())
    );
  }
}
