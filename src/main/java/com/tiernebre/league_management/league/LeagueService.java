package com.tiernebre.league_management.league;

import com.tiernebre.util.pagination.Page;
import com.tiernebre.util.pagination.PageRequest;

public interface LeagueService {
  public League create(long accountId, UserLeagueRequest request);

  public Page<League> pageForAccount(long accountId, PageRequest request);
}
