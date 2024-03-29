package com.tiernebre.web.controllers.league_management;

import com.tiernebre.league_management.league.LeagueService;
import com.tiernebre.league_management.league.UserLeagueRequest;
import com.tiernebre.web.templates.CreateLeague;
import com.tiernebre.web.templates.Leagues;
import com.tiernebre.web.util.WebHelper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LeagueController {

  private static final Logger LOG = LoggerFactory.getLogger(
    LeagueController.class
  );

  private final LeagueService service;
  private final WebHelper helper;

  public LeagueController(LeagueService service, WebHelper helper) {
    this.service = service;
    this.helper = helper;
  }

  public void leagues(Context ctx) {
    helper.template(ctx, new Leagues());
  }

  public void form(Context ctx) {
    helper.template(ctx, new CreateLeague());
  }

  public void create(Context ctx) {
    service
      .create(
        helper.authenticatedSession(ctx).accountId(),
        new UserLeagueRequest(ctx.formParam(Constants.LEAGUE_NAME_FIELD_NAME))
      )
      .peek(league -> {
        LOG.debug("Successfully created league {}.", league);
        ctx.status(HttpStatus.CREATED);
        ctx.result("Created league");
      })
      .peekLeft(error -> {
        LOG.debug("Failed to create a league, encountered error {}", error);
        ctx.status(error.httpStatus());
        ctx.result(error.publicMessage());
      });
  }
}
