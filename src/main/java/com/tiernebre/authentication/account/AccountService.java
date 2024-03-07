package com.tiernebre.authentication.account;

import io.vavr.control.Either;

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
  public Either<String, Account> getForGoogleAccountId(String googleAccountId);
}
