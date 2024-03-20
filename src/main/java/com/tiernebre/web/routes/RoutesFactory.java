package com.tiernebre.web.routes;

import com.tiernebre.context.DependencyContext;
import com.tiernebre.web.controllers.DefaultControllerHelper;
import com.tiernebre.web.controllers.HealthController;
import com.tiernebre.web.controllers.IndexController;
import com.tiernebre.web.controllers.authentication.LoginController;
import com.tiernebre.web.controllers.authentication.LogoutController;
import com.tiernebre.web.controllers.authentication.RegistrationController;
import com.tiernebre.web.routes.api.ApiRoutes;
import com.tiernebre.web.routes.api.HealthRoutes;

public final class RoutesFactory {

  private final DependencyContext dependencyContext;

  public RoutesFactory(DependencyContext dependencyContext) {
    this.dependencyContext = dependencyContext;
  }

  public Routes create() {
    var helper = new DefaultControllerHelper();
    var authentication = dependencyContext.authentication();
    var web = dependencyContext.web();
    return new Routes(
      new ApiRoutes(new HealthRoutes(new HealthController())),
      new PageRoutes(
        new IndexController(helper),
        new RegistrationRoutes(
          new RegistrationController(
            authentication.registrationService(),
            authentication.registrationAuthenticationStrategy(),
            web.sessionRegistry()
          )
        ),
        new LoginRoutes(
          new LoginController(
            helper,
            authentication.googleAuthenticationStrategy(),
            authentication.registrationAuthenticationStrategy(),
            web.sessionRegistry()
          )
        ),
        new LogoutRoutes(new LogoutController(web.sessionRegistry(), helper))
      )
    );
  }
}
