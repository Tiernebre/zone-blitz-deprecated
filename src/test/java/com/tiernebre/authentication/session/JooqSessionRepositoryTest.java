package com.tiernebre.authentication.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.tiernebre.database.TestJooqDslContextFactory;
import org.junit.Test;

public final class JooqSessionRepositoryTest {

  private final JooqSessionRepository repository = new JooqSessionRepository(
    TestJooqDslContextFactory.createTestDSLContext()
  );

  @Test
  public void insertOne() {
    String accountId = "3141592653589793238";
    var session = repository.insertOne(accountId);
    assertEquals(accountId, session.accountId());
    assertNotNull(session.id());
  }
}
