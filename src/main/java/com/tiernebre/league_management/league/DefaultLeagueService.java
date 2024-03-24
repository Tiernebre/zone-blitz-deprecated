package com.tiernebre.league_management.league;

import com.tiernebre.util.pagination.Page;
import com.tiernebre.util.pagination.PageRequest;

public final class DefaultLeagueService implements LeagueService {

  private final LeagueRepository repository;

  public DefaultLeagueService(LeagueRepository repository) {
    this.repository = repository;
  }

  @Override
  public League create(long accountId, UserLeagueRequest request) {
    return repository.insertOne(new InsertLeagueRequest(accountId, request));
  }

  @Override
  public Page<League> pageForAccount(long accountId, PageRequest request) {
    return null;
  }
}
