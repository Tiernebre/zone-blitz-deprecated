package com.tiernebre.league_management.league;

import org.jooq.DSLContext;

public final class JooqLeagueRepository implements LeagueRepository {

  private final DSLContext dsl;

  public JooqLeagueRepository(DSLContext dsl) {
    this.dsl = dsl;
  }

  @Override
  public League insertOne(InsertLeagueRequest request) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createOne'");
  }
}
