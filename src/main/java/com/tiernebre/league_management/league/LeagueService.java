package com.tiernebre.league_management.league;

import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.pagination.Page;
import com.tiernebre.util.pagination.PageRequest;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface LeagueService {
  public Either<ZoneBlitzError, League> create(
    long accountId,
    UserLeagueRequest request
  );

  public Page<League> pageForAccount(long accountId, PageRequest request);

  public Option<League> getForAccount(long id, long accountId);
}
