package com.tiernebre.authentication.account;

import io.vavr.control.Option;

public interface AccountRepository {
  public Account insertOne(String googleAccountId, Long registrationId);

  public Option<Account> selectOneByGoogleAccountId(String googleAccountId);

  public Option<Account> selectOneByRegistrationId(long registrationId);
}
