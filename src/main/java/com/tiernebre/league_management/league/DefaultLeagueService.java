package com.tiernebre.league_management.league;

import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.pagination.Page;
import com.tiernebre.util.pagination.PageRequest;
import io.vavr.control.Either;
import io.vavr.control.Option;

public final class DefaultLeagueService implements LeagueService {

  private final LeagueRepository repository;
  private final LeagueValidator validator;

  public DefaultLeagueService(
    LeagueRepository repository,
    LeagueValidator validator
  ) {
    this.repository = repository;
    this.validator = validator;
  }

  @Override
  public Either<ZoneBlitzError, League> create(
    long accountId,
    UserLeagueRequest request
  ) {
    return validator
      .validateUserRequest(request)
      .map(req -> new InsertLeagueRequest(accountId, req))
      .flatMap(repository::insertOne);
  }

  @Override
  public Page<League> pageForAccount(long accountId, PageRequest request) {
    return repository.selectForAccount(accountId, request);
  }

  @Override
  public Option<League> getForAccount(long id, long accountId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'getForAccount'"
    );
  }
}
