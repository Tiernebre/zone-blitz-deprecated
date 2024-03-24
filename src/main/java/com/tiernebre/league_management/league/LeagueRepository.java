package com.tiernebre.league_management.league;

import com.tiernebre.database.RepositoryCollection;
import com.tiernebre.util.pagination.PageRequest;

public interface LeagueRepository {
  public League insertOne(InsertLeagueRequest request);

  public RepositoryCollection<League> selectForAccount(
    long accountId,
    PageRequest request
  );
}
