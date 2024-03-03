package com.tiernebre.web;

import com.tiernebre.authentication.AuthenticationContext;
import com.tiernebre.web.controllers.FrontPageController;
import com.tiernebre.web.controllers.GoogleAuthenticationController;
import com.tiernebre.web.controllers.HealthController;
import com.tiernebre.web.controllers.LoginPageController;

public final class RouterFactory {

  private final AuthenticationContext authenticationContext;

  public RouterFactory(AuthenticationContext authenticationContext) {
    this.authenticationContext = authenticationContext;
  }

  public Router create() {
    return new Router(
      new FrontPageController(),
      new HealthController(),
      new LoginPageController(
        authenticationContext.configuration().oauthGoogleClientId()
      ),
      new GoogleAuthenticationController(
        authenticationContext.googleAuthenticationService()
      )
    );
  }
}
