package com.tiernebre.web.routes;

import com.tiernebre.context.DependencyContext;
import com.tiernebre.web.controllers.FrontPageController;
import com.tiernebre.web.controllers.HealthController;
import com.tiernebre.web.controllers.LoginPageController;
import com.tiernebre.web.controllers.authentication.GoogleAuthenticationController;
import com.tiernebre.web.controllers.authentication.RegistrationController;
import com.tiernebre.web.routes.api.ApiRoutes;
import com.tiernebre.web.routes.api.AuthenticationRoutes;
import com.tiernebre.web.routes.api.HealthRoutes;
import com.tiernebre.web.routes.api.RegistrationRoutes;
import com.tiernebre.web.routes.page.PageRoutes;

public final class RoutesFactory {

  private final DependencyContext dependencyContext;

  public RoutesFactory(DependencyContext dependencyContext) {
    this.dependencyContext = dependencyContext;
  }

  public Routes create() {
    var authentication = dependencyContext.authentication();
    return new Routes(
      new ApiRoutes(
        new AuthenticationRoutes(
          new GoogleAuthenticationController(
            authentication.googleAuthenticationService()
          )
        ),
        new HealthRoutes(new HealthController()),
        new RegistrationRoutes(
          new RegistrationController(authentication.registrationService())
        )
      ),
      new PageRoutes(
        new FrontPageController(),
        new LoginPageController(
          dependencyContext
            .authentication()
            .configuration()
            .oauthGoogleClientId()
        )
      )
    );
  }
}
