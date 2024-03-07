package com.tiernebre.authentication.session;

import com.tiernebre.authentication.account.Account;
import io.vavr.control.Option;
import java.util.UUID;

public final class DefaultSessionService implements SessionService {

  private final SessionRepository repository;

  public DefaultSessionService(SessionRepository repository) {
    this.repository = repository;
  }

  @Override
  public Session create(Account account) {
    return repository.insertOne(account.id());
  }

  @Override
  public Option<Session> get(UUID id) {
    return repository.selectOne(id);
  }
}