package com.tiernebre.authentication.session;

import com.tiernebre.database.jooq.Tables;
import java.util.Optional;
import java.util.UUID;
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

  @Override
  public Optional<Session> selectOne(UUID id) {
    return Optional.ofNullable(
      dsl.fetchOne(Tables.SESSION, Tables.SESSION.ID.eq(id))
    ).map(result -> result.into(Session.class));
  }
}
