package com.tiernebre.league_management.league;

import com.tiernebre.database.jooq.Tables;
import com.tiernebre.util.pagination.PageRequest;
import java.util.Collection;
import org.jooq.DSLContext;

public final class JooqLeagueRepository implements LeagueRepository {

  private final DSLContext dsl;

  public JooqLeagueRepository(DSLContext dsl) {
    this.dsl = dsl;
  }

  @Override
  public League insertOne(InsertLeagueRequest request) {
    return dsl
      .insertInto(Tables.LEAGUE, Tables.LEAGUE.ACCOUNT_ID, Tables.LEAGUE.NAME)
      .values(request.accountId(), request.userRequest().name())
      .returning()
      .fetchSingleInto(League.class);
  }

  @Override
  public Collection<League> selectForAccount(
    long accountId,
    PageRequest request
  ) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'selectForAccount'"
    );
  }
}
