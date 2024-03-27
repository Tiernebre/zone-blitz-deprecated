package com.tiernebre.league_management;

import com.tiernebre.database.DatabaseContext;
import com.tiernebre.league_management.league.DefaultLeagueService;
import com.tiernebre.league_management.league.JooqLeagueRepository;
import com.tiernebre.league_management.league.VavrLeagueValidator;

public final class LeagueManagementContextFactory {

  private final DatabaseContext database;

  public LeagueManagementContextFactory(DatabaseContext database) {
    this.database = database;
  }

  public LeagueManagementContext create() {
    return new LeagueManagementContext(
      new DefaultLeagueService(
        new JooqLeagueRepository(
          database.dslContext(),
          database.jooqRepositoryPaginationStrategy()
        ),
        new VavrLeagueValidator()
      )
    );
  }
}
