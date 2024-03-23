package com.tiernebre.authentication.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tiernebre.database.JooqDatabaseTest;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class JooqAccountRepositoryTest extends JooqDatabaseTest {

  private final AccountRepository repository = context.accountRepository();

  @Test
  public void insertOne() {
    var id = UUID.randomUUID().toString();
    var insertedAccount = repository.insertOne(id, null);
    assertNotNull(insertedAccount);
    assertNotNull(insertedAccount.id());
    assertEquals(id, insertedAccount.googleAccountId());
    assertNull(insertedAccount.registrationId());
  }

  @Test
  public void selectOneByGoogleAccountIdExists() {
    var insertedAccount = repository.insertOne(
      UUID.randomUUID().toString(),
      null
    );
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
