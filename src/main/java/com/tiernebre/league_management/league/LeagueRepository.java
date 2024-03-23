package com.tiernebre.league_management.league;

import com.tiernebre.util.pagination.PageRequest;
import java.util.Collection;

public interface LeagueRepository {
  public League insertOne(InsertLeagueRequest request);

  public Collection<League> selectForAccount(
    long accountId,
    PageRequest request
  );
}
