package com.tiernebre.web.routes.league_management;

import io.javalin.apibuilder.EndpointGroup;

public final class LeagueManagementRoutes implements EndpointGroup {

  private final LeagueRoutes leagueRoutes;

  public LeagueManagementRoutes(LeagueRoutes leagueRoutes) {
    this.leagueRoutes = leagueRoutes;
  }

  @Override
  public void addEndpoints() {
    leagueRoutes.addEndpoints();
  }
}
