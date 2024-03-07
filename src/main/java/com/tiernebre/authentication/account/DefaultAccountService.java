package com.tiernebre.authentication.account;

import io.vavr.control.Either;
import io.vavr.control.Option;

public final class DefaultAccountService implements AccountService {

  private final AccountRepository repository;

  public DefaultAccountService(AccountRepository accountRepository) {
    this.repository = accountRepository;
  }

  @Override
  public Either<String, Account> getForGoogleAccountId(String googleAccountId) {
    return Option.of(googleAccountId)
      .toEither("Given Google account id is null.")
      .map(this::selectOrCreateByGoogleAccountId);
  }

  private Account selectOrCreateByGoogleAccountId(String accountId) {
    return repository
      .selectOneByGoogleAccountId(accountId)
      .getOrElse(() -> repository.insertOne(accountId));
  }
}
