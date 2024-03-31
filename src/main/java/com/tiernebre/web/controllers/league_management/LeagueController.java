package com.tiernebre.web.controllers.league_management;

import com.tiernebre.league_management.league.League;
import com.tiernebre.league_management.league.LeagueService;
import com.tiernebre.league_management.league.UserLeagueRequest;
import com.tiernebre.web.templates.CreateLeague;
import com.tiernebre.web.templates.LeagueTemplate;
import com.tiernebre.web.templates.Leagues;
import com.tiernebre.web.util.WebHelper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.http.NotFoundResponse;
import io.vavr.Tuple2;
import io.vavr.control.Option;
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
    var leagues = service.pageForAccount(
      helper.authenticatedSession(ctx).accountId(),
      helper.pageRequest(ctx)
    );
    helper.template(ctx, new Leagues(leagues.edges()));
  }

  public void form(Context ctx) {
    formPage(ctx, null);
  }

  public void create(Context ctx) {
    service
      .create(
        helper.authenticatedSession(ctx).accountId(),
        new UserLeagueRequest(
          ctx.formParam(LeagueManagementWebConstants.LEAGUE_NAME_FIELD_NAME)
        )
      )
      .peek(league -> {
        LOG.debug("Successfully created league {}.", league);
        ctx.status(HttpStatus.CREATED);
        ctx.redirect(LeagueWebConstants.LEAGUES_URI);
      })
      .peekLeft(error -> {
        LOG.debug("Failed to create a league, encountered error {}", error);
        ctx.status(error.httpStatus());
        formPage(ctx, error.publicMessage());
      });
  }

  public void get(Context ctx) {
    Option.of(ctx.pathParam(LeagueWebConstants.LEAGUE_ID_PATH_PARAM))
      .toTry()
      .mapTry(Long::parseLong)
      .toOption()
      .map(id -> new Tuple2<>(id, helper.authenticatedSession(ctx).accountId()))
      .flatMap(ids -> service.getForAccount(ids._1, ids._2))
      .peek(league -> {
        LOG.debug("Got league {}", league);
        leaguePage(ctx, league);
      })
      .onEmpty(() -> {
        LOG.debug("Could not find league");
      })
      .getOrElseThrow(() -> new NotFoundResponse());
  }

  private void formPage(Context ctx, String error) {
    helper.template(ctx, new CreateLeague(error));
  }

  private void leaguePage(Context ctx, League league) {
    helper.template(ctx, new LeagueTemplate(league));
  }
}
