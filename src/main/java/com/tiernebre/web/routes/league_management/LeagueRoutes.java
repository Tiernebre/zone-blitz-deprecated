package com.tiernebre.web.routes.league_management;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.tiernebre.web.constants.WebUserRole;
import com.tiernebre.web.controllers.league_management.LeagueController;
import io.javalin.apibuilder.EndpointGroup;

public final class LeagueRoutes implements EndpointGroup {

  private static final String URI = "leagues";
  private final LeagueController controller;

  public LeagueRoutes(LeagueController controller) {
    this.controller = controller;
  }

  @Override
  public void addEndpoints() {
    get(URI, controller::page, WebUserRole.LOGGED_IN);
    post(URI, controller::create, WebUserRole.LOGGED_IN);
  }
}
