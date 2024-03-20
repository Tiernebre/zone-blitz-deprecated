package com.tiernebre.authentication.session;

import com.tiernebre.authentication.account.Account;
import io.vavr.control.Option;
import java.util.UUID;

public interface SessionService {
  /**
   * @param account The account to create a session for.
   * @return The created session.
   */
  public Session create(Account account);

  /**
   * @param id The id or "token" of the session to get
   * @return The found session, empty if it could not be found.
   */
  public Option<Session> get(UUID id);

  /**
   * Deletes a session by its given id.
   *
   * @param id The id or "token" of the session to delete
   */
  public Option<Session> delete(UUID id);
}
