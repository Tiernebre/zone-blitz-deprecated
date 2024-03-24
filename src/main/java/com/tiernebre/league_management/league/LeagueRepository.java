package com.tiernebre.league_management.league;

import com.tiernebre.util.pagination.Page;
import com.tiernebre.util.pagination.PageRequest;

public interface LeagueRepository {
  public League insertOne(InsertLeagueRequest request);

  public Page<League> selectForAccount(long accountId, PageRequest request);
}
