package com.tiernebre.authentication.session;

import io.vavr.control.Option;
import java.util.UUID;

public interface SessionRepository {
  public Session insertOne(long accountId);

  /**
   * Fetches a Session by its unique id value. The unique ID value of a session is also known as its "token" that is stored on the client device.
   * @param id The id or "token" of the session to fetch.
   * @return A session if it exists, otherwise an empty option.
   */
  public Option<Session> selectOne(UUID id);
}
