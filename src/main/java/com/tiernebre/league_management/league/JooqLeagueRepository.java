package com.tiernebre.league_management.league;

import com.tiernebre.database.JooqRepositoryPaginationStrategy;
import com.tiernebre.database.jooq.Tables;
import com.tiernebre.util.pagination.Page;
import com.tiernebre.util.pagination.PageRequest;
import java.util.List;
import org.jooq.DSLContext;

public final class JooqLeagueRepository implements LeagueRepository {

  private final DSLContext dsl;
  private final JooqRepositoryPaginationStrategy paginationStrategy;

  public JooqLeagueRepository(
    DSLContext dsl,
    JooqRepositoryPaginationStrategy paginationStrategy
  ) {
    this.dsl = dsl;
    this.paginationStrategy = paginationStrategy;
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
  public Page<League> selectForAccount(long accountId, PageRequest request) {
    return paginationStrategy.seek(
      Tables.LEAGUE,
      Tables.LEAGUE.ID,
      request,
      League.class,
      List.of(Tables.LEAGUE.ACCOUNT_ID.eq(accountId))
    );
  }
}
