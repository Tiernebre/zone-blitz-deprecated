package com.tiernebre.authentication.session;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository {
  public Session insertOne(String accountId);

  public Optional<Session> selectOne(UUID id);
}
