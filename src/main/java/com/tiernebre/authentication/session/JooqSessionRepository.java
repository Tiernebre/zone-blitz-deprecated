package com.tiernebre.authentication.session;

import com.tiernebre.database.jooq.Tables;
import org.jooq.DSLContext;

public final class JooqSessionRepository implements SessionRepository {

  private final DSLContext dsl;

  public JooqSessionRepository(DSLContext dsl) {
    this.dsl = dsl;
  }

  @Override
  public Session insertOne(String accountId) {
    return dsl
      .insertInto(Tables.SESSION, Tables.SESSION.ACCOUNT_ID)
      .values(accountId)
      .returning()
      .fetchSingleInto(Session.class);
  }
}
