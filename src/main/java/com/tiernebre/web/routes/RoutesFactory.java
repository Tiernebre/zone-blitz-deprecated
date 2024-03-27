package com.tiernebre.web.routes;

import com.tiernebre.context.DependencyContext;
import com.tiernebre.web.controllers.HealthController;
import com.tiernebre.web.controllers.IndexController;
import com.tiernebre.web.controllers.authentication.LoginController;
import com.tiernebre.web.controllers.authentication.LogoutController;
import com.tiernebre.web.controllers.authentication.RegistrationController;
import com.tiernebre.web.controllers.league_management.LeagueController;
import com.tiernebre.web.routes.api.ApiRoutes;
import com.tiernebre.web.routes.api.HealthRoutes;
import com.tiernebre.web.routes.league_management.LeagueManagementRoutes;
import com.tiernebre.web.routes.league_management.LeagueRoutes;

public final class RoutesFactory {

  private final DependencyContext dependencyContext;

  public RoutesFactory(DependencyContext dependencyContext) {
    this.dependencyContext = dependencyContext;
  }

  public Routes create() {
    var authentication = dependencyContext.authentication();
    var web = dependencyContext.web();
    var helper = web.helper();
    return new Routes(
      new ApiRoutes(new HealthRoutes(new HealthController())),
      new PageRoutes(
        new IndexController(web.helper()),
        new RegistrationRoutes(
          new RegistrationController(
            authentication.registrationService(),
            authentication.registrationAuthenticationStrategy(),
            web.sessionRegistry(),
            helper
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
        new LogoutRoutes(
          new LogoutController(web.sessionRegistry(), web.helper())
        )
      ),
      new LeagueManagementRoutes(
        new LeagueRoutes(
          new LeagueController(
            dependencyContext.leagueManagement().leagueService(),
            helper
          )
        )
      )
    );
  }
}
