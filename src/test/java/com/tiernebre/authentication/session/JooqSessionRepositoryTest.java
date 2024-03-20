package com.tiernebre.authentication.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.tiernebre.authentication.account.AccountRepository;
import com.tiernebre.authentication.account.JooqAccountRepository;
import com.tiernebre.database.TestJooqDslContextFactory;
import com.tiernebre.database.jooq.Tables;
import java.time.LocalDateTime;
import java.util.UUID;
import org.jooq.DSLContext;
import org.junit.Test;

public final class JooqSessionRepositoryTest {

  private final DSLContext dsl =
    TestJooqDslContextFactory.createTestDSLContext();
  private final JooqSessionRepository repository = new JooqSessionRepository(
    dsl
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
      .set(Tables.SESSION.EXPIRES_AT, LocalDateTime.of(1, 1, 1, 1, 1))
      .where(Tables.SESSION.ID.eq(existingSession.id()))
      .execute();
    assertTrue(repository.selectOne(existingSession.id()).isEmpty());
  }
}
