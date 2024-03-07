package com.tiernebre.authentication.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.tiernebre.database.TestJooqDslContextFactory;
import java.util.UUID;
import org.jooq.DSLContext;
import org.junit.Test;

public class JooqAccountRepositoryTest {

  private final DSLContext dsl =
    TestJooqDslContextFactory.createTestDSLContext();
  private final AccountRepository repository = new JooqAccountRepository(dsl);

  @Test
  public void insertOne() {
    var id = UUID.randomUUID().toString();
    var insertedAccount = repository.insertOne(id);
    assertNotNull(insertedAccount);
    assertNotNull(insertedAccount.id());
    assertEquals(id, insertedAccount.googleAccountId());
    assertNull(insertedAccount.registrationId());
  }

  @Test
  public void selectOneByGoogleAccountIdExists() {
    var insertedAccount = repository.insertOne(UUID.randomUUID().toString());
    var foundAccount = repository.selectOneByGoogleAccountId(
      insertedAccount.googleAccountId()
    );
    assertFalse(foundAccount.isEmpty());
    assertEquals(insertedAccount, foundAccount.get());
  }

  @Test
  public void selectOneByGoogleAccountIdNone() {
    assertTrue(
      repository
        .selectOneByGoogleAccountId(UUID.randomUUID().toString())
        .isEmpty()
    );
  }
}
