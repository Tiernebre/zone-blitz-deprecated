package com.tiernebre.web.routers;

import com.tiernebre.authentication.AuthenticationContext;
import com.tiernebre.web.controllers.FrontPageController;
import com.tiernebre.web.controllers.HealthController;
import com.tiernebre.web.controllers.LoginPageController;
import com.tiernebre.web.controllers.authentication.GoogleAuthenticationController;
import com.tiernebre.web.routers.api.ApiRoutes;
import com.tiernebre.web.routers.api.AuthenticationRoutes;
import com.tiernebre.web.routers.api.HealthRoutes;
import com.tiernebre.web.routers.page.PageRoutes;

public final class RoutesFactory {

  private final AuthenticationContext authenticationContext;

  public RoutesFactory(AuthenticationContext authenticationContext) {
    this.authenticationContext = authenticationContext;
  }

  public Routes create() {
    return new Routes(
      new ApiRoutes(
        new AuthenticationRoutes(
          new GoogleAuthenticationController(
            authenticationContext.googleAuthenticationService()
          )
        ),
        new HealthRoutes(new HealthController())
      ),
      new PageRoutes(
        new FrontPageController(),
        new LoginPageController(
          authenticationContext.configuration().oauthGoogleClientId()
        )
      )
    );
  }
}
