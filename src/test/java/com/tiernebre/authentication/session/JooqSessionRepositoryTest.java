package com.tiernebre.authentication.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.tiernebre.database.TestJooqDslContextFactory;
import java.util.UUID;
import org.jooq.DSLContext;
import org.junit.Test;

public final class JooqSessionRepositoryTest {

  private final DSLContext dsl =
    TestJooqDslContextFactory.createTestDSLContext();
  private final JooqSessionRepository repository = new JooqSessionRepository(
    dsl
  );

  @Test
  public void insertOne() {
    String accountId = UUID.randomUUID().toString();
    var session = repository.insertOne(accountId);
    assertEquals(accountId, session.accountId());
    assertNotNull(session.id());
  }

  @Test
  public void selectOne() {
    String accountId = UUID.randomUUID().toString();
    var existingSession = repository.insertOne(accountId);
    assertEquals(accountId, existingSession.accountId());
    assertNotNull(existingSession.id());
  }
}
