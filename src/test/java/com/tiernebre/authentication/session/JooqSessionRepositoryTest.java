package com.tiernebre.authentication.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.tiernebre.authentication.account.AccountRepository;
import com.tiernebre.authentication.account.JooqAccountRepository;
import com.tiernebre.database.TestJooqDslContextFactory;
import com.tiernebre.database.jooq.Tables;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import org.jooq.DSLContext;
import org.junit.Test;

public final class JooqSessionRepositoryTest {

  private final DSLContext dsl =
    TestJooqDslContextFactory.createTestDSLContext();
  private final Clock clock = Clock.fixed(
    Instant.now(),
    ZoneId.systemDefault()
  );
  private final JooqSessionRepository repository = new JooqSessionRepository(
    dsl,
    clock
  );
  private final AccountRepository accountRepository = new JooqAccountRepository(
    dsl
  );

  @Test
  public void insertOne() {
    var accountId = accountRepository
      .insertOne(UUID.randomUUID().toString(), null)
      .id();
    var session = repository.insertOne(accountId);
    assertEquals(accountId, session.accountId());
    assertNotNull(session.id());
    assertEquals(
      -1,
      LocalDateTime.now()
        .plusMinutes(59)
        .plusSeconds(59)
        .compareTo(session.expiresAt())
    );
    assertEquals(
      1,
      LocalDateTime.now().plusMinutes(60).compareTo(session.expiresAt())
    );
  }

  @Test
  public void selectOne() {
    var existingSession = repository.insertOne(
      accountRepository.insertOne(UUID.randomUUID().toString(), null).id()
    );
    var selectedSession = repository.selectOne(existingSession.id());
    assertEquals(existingSession, selectedSession.get());
  }

  @Test
  public void selectOneCanReturnEmpty() {
    assertTrue(repository.selectOne(UUID.randomUUID()).isEmpty());
  }

  @Test
  public void selectOneReturnsEmptyForExpiredSession() {
    var existingSession = repository.insertOne(
      accountRepository.insertOne(UUID.randomUUID().toString(), null).id()
    );
    dsl
      .update(Tables.SESSION)
      .set(
        Tables.SESSION.EXPIRES_AT,
        existingSession.expiresAt().minusHours(1).minusSeconds(1)
      )
      .where(Tables.SESSION.ID.eq(existingSession.id()))
      .execute();
    assertTrue(repository.selectOne(existingSession.id()).isEmpty());
  }

  @Test
  public void selectOneReturnsEmptyForRevokedSession() {
    var existingSession = repository.insertOne(
      accountRepository.insertOne(UUID.randomUUID().toString(), null).id()
    );
    dsl
      .update(Tables.SESSION)
      .set(Tables.SESSION.REVOKED, true)
      .where(Tables.SESSION.ID.eq(existingSession.id()))
      .execute();
    assertTrue(repository.selectOne(existingSession.id()).isEmpty());
  }

  @Test
  public void deleteOne() {
    var existingSession = repository.insertOne(
      accountRepository.insertOne(UUID.randomUUID().toString(), null).id()
    );
    assertFalse(existingSession.revoked());
    var deletedSession = repository.deleteOne(existingSession.id());
    assertTrue(deletedSession.isDefined());
    assertTrue(deletedSession.get().revoked());
    var updatedSession = dsl.fetchOne(
      Tables.SESSION,
      Tables.SESSION.ID.eq(existingSession.id())
    );
    assertTrue(updatedSession.getRevoked());
  }

  @Test
  public void deleteOneForNonExistent() {
    var deletedSession = repository.deleteOne(UUID.randomUUID());
    assertTrue(deletedSession.isEmpty());
  }
}
