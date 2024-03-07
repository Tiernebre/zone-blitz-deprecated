package com.tiernebre.authentication.session;

import com.tiernebre.authentication.account.Account;

public interface SessionService {
  /**
   * @param account The account to create a session for.
   * @return The created session.
   */
  public Session create(Account account);
}
