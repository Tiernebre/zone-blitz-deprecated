package com.tiernebre.authentication.session;

import com.tiernebre.database.jooq.Tables;
import com.tiernebre.database.jooq.tables.records.SessionRecord;
import io.vavr.control.Option;
import java.time.LocalDateTime;
import java.util.UUID;
import org.jooq.DSLContext;

public final class JooqSessionRepository implements SessionRepository {

  private final DSLContext dsl;

  public JooqSessionRepository(DSLContext dsl) {
    this.dsl = dsl;
  }

  @Override
  public Session insertOne(long accountId) {
    return dsl
      .insertInto(Tables.SESSION, Tables.SESSION.ACCOUNT_ID)
      .values(accountId)
      .returning()
      .fetchSingleInto(Session.class);
  }

  @Override
  public Option<Session> deleteOne(UUID id) {
    return fetchOneById(id)
      .map(existing -> {
        existing.setRevoked(true);
        return existing;
      })
      .peek(SessionRecord::store)
      .map(this::mapRecord);
  }

  @Override
  public Option<Session> selectOne(UUID id) {
    return fetchOneById(id).map(this::mapRecord);
  }

  private Option<SessionRecord> fetchOneById(UUID id) {
    return Option.of(
      dsl.fetchOne(
        Tables.SESSION,
        Tables.SESSION.ID.eq(id),
        Tables.SESSION.EXPIRES_AT.greaterOrEqual(LocalDateTime.now()),
        Tables.SESSION.REVOKED.eq(false)
      )
    );
  }

  private Session mapRecord(SessionRecord record) {
    return record.into(Session.class);
  }
}
