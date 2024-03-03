package com.tiernebre.authentication.session;

import java.util.UUID;

public interface SessionRepository {
  public Session insertOne(String accountId);

  public Session getOne(UUID id);
}
