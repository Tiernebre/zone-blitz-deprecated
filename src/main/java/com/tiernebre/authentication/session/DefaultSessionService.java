package com.tiernebre.authentication.session;

import com.tiernebre.authentication.account.Account;

public final class DefaultSessionService implements SessionService {

  private final SessionRepository repository;

  public DefaultSessionService(SessionRepository repository) {
    this.repository = repository;
  }

  @Override
  public Session create(Account account) {
    return repository.insertOne(account.id());
  }
}
