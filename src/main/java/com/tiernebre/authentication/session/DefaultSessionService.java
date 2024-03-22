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
    return create(account.id());
  }

  @Override
  public Session create(long accountId) {
    return repository.insertOne(accountId);
  }

  @Override
  public Option<Session> get(UUID id) {
    return repository.selectOne(id);
  }

  @Override
  public Option<Session> delete(UUID id) {
    return repository.deleteOne(id);
  }
}
