package com.tiernebre.league_management.league;

import com.tiernebre.database.jooq.Tables;
import com.tiernebre.util.pagination.CursorMapper;
import com.tiernebre.util.pagination.PageRequest;
import java.util.Collection;
import org.jooq.DSLContext;

public final class JooqLeagueRepository implements LeagueRepository {

  private final DSLContext dsl;
  private final CursorMapper cursorMapper;

  public JooqLeagueRepository(DSLContext dsl, CursorMapper cursorMapper) {
    this.dsl = dsl;
    this.cursorMapper = cursorMapper;
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
    return dsl
      .select()
      .from(Tables.LEAGUE)
      .where(
        Tables.LEAGUE.ID.greaterThan(cursorMapper.cursorToId(request.after()))
      )
      .and(Tables.LEAGUE.ACCOUNT_ID.eq(accountId))
      .orderBy(Tables.LEAGUE.ID, Tables.LEAGUE.ID.desc())
      .limit(request.first())
      .fetchInto(League.class);
  }
}
