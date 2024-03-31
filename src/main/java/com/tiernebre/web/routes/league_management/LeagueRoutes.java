package com.tiernebre.web.routes.league_management;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.tiernebre.web.constants.WebUserRole;
import com.tiernebre.web.controllers.league_management.LeagueController;
import com.tiernebre.web.controllers.league_management.LeagueWebConstants;
import io.javalin.apibuilder.EndpointGroup;

public final class LeagueRoutes implements EndpointGroup {

  private static final String CREATE_URI = String.format(
    "%s/create",
    LeagueWebConstants.LEAGUES_URI
  );
  private final LeagueController controller;

  public LeagueRoutes(LeagueController controller) {
    this.controller = controller;
  }

  @Override
  public void addEndpoints() {
    get(
      LeagueWebConstants.LEAGUES_URI,
      controller::leagues,
      WebUserRole.LOGGED_IN
    );
    post(CREATE_URI, controller::create, WebUserRole.LOGGED_IN);
    get(CREATE_URI, controller::form, WebUserRole.LOGGED_IN);
  }
}
