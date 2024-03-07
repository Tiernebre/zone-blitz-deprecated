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
  public Account insertOne(String googleAccountId) {
    return dsl
      .insertInto(Tables.ACCOUNT, Tables.ACCOUNT.GOOGLE_ACCOUNT_ID)
      .values(googleAccountId)
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
}
