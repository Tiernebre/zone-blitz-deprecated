package com.tiernebre.authentication.session;

public interface SessionRepository {
  public Session insertOne(String accountId);
}
