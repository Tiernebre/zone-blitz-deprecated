package com.tiernebre.authentication.account;

import com.tiernebre.database.jooq.Tables;
import io.vavr.control.Option;
import org.jooq.DSLContext;

public final class JooqAccountRepository implements AccountRepository {

  private final DSLContext dsl;

  public JooqAccountRepository(DSLContext dsl) {
    this.dsl = dsl;
  }

  @Override
  public Account insertOne(String googleAccountId, Long registrationId) {
    return dsl
      .insertInto(
        Tables.ACCOUNT,
        Tables.ACCOUNT.GOOGLE_ACCOUNT_ID,
        Tables.ACCOUNT.REGISTRATION_ID
      )
      .values(googleAccountId, registrationId)
      .returning()
      .fetchSingleInto(Account.class);
  }

  @Override
  public Option<Account> selectOneByGoogleAccountId(String googleAccountId) {
    return Option.of(
      dsl.fetchOne(
        Tables.ACCOUNT,
        Tables.ACCOUNT.GOOGLE_ACCOUNT_ID.eq(googleAccountId)
      )
    ).map(result -> result.into(Account.class));
  }

  @Override
  public Option<Account> selectOneByRegistrationId(long registrationId) {
    return Option.of(
      dsl.fetchOne(
        Tables.ACCOUNT,
        Tables.ACCOUNT.REGISTRATION_ID.eq(registrationId)
      )
    ).map(result -> result.into(Account.class));
  }
}
