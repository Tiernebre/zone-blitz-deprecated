package com.tiernebre.authentication.account;

import io.vavr.control.Option;

public interface AccountRepository {
  public Account insertOne(String googleAccountId);

  public Option<Account> selectOneByGoogleAccountId(String googleAccountId);
}
