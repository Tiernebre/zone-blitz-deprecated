package com.tiernebre.league_management.league;

import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.pagination.Page;
import com.tiernebre.util.pagination.PageRequest;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface LeagueRepository {
  public Either<ZoneBlitzError, League> insertOne(InsertLeagueRequest request);

  public Page<League> selectForAccount(long accountId, PageRequest request);

  public Option<League> selectOneForAccount(long id, long accountId);
}
