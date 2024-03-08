package com.tiernebre.authentication.account;

import io.vavr.control.Either;
import io.vavr.control.Option;

public interface AccountService {
  /**
   * Will get an existing Account by an associated Google account id.
   *
   * If the account does not exist, a new account is created with the given
   * associated Google account id.
   *
   * @param googleAccountId The associated Google account id.
   * @return An error message if an error occurred, or the existing / created account.
   */
  public Either<String, Account> getForGoogleAccount(String googleAccountId);

  /**
   * Will get an existing Account by an associated registration id.
   *
   * @param googleAccountId The associated registration id.
   * @return Empty if an account does not exist for the given registration id, or the existing account.
   */
  public Option<Account> getForRegistration(long registrationId);
}
