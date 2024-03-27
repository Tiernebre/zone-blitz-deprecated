package com.tiernebre.web.routes;

import com.tiernebre.web.routes.api.ApiRoutes;
import com.tiernebre.web.routes.league_management.LeagueManagementRoutes;
import io.javalin.apibuilder.EndpointGroup;

public final class Routes implements EndpointGroup {

  private final ApiRoutes apiRoutes;
  private final PageRoutes pageRoutes;
  private final LeagueManagementRoutes leagueManagementRoutes;

  public Routes(
    ApiRoutes apiRoutes,
    PageRoutes pageRoutes,
    LeagueManagementRoutes leagueManagementRoutes
  ) {
    this.apiRoutes = apiRoutes;
    this.pageRoutes = pageRoutes;
    this.leagueManagementRoutes = leagueManagementRoutes;
  }

  @Override
  public void addEndpoints() {
    pageRoutes.addEndpoints();
    apiRoutes.addEndpoints();
    leagueManagementRoutes.addEndpoints();
  }
}
