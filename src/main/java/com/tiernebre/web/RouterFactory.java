package com.tiernebre.web;

import com.tiernebre.web.controllers.AuthenticationController;
import com.tiernebre.web.controllers.FrontPageController;
import com.tiernebre.web.controllers.HealthController;
import com.tiernebre.web.controllers.LoginPageController;

public final class RouterFactory {

  public Router create() {
    return new Router(
      new FrontPageController(),
      new HealthController(),
      new LoginPageController(),
      new AuthenticationController()
    );
  }
}
