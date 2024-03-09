package com.tiernebre.authentication.registration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.tiernebre.database.TestJooqDslContextFactory;
import java.util.UUID;
import org.junit.Test;

public final class JooqRegistrationRepositoryTest {

  private final RegistrationRepository repository =
    new JooqRegistrationRepository(
      TestJooqDslContextFactory.createTestDSLContext()
    );

  @Test
  public void insertOne() {
    var username = UUID.randomUUID().toString();
    var password = UUID.randomUUID().toString();
    var inserted = repository.insertOne(username, password);
    assertNotNull(inserted.id());
    assertEquals(username, inserted.username());
    assertEquals(password, inserted.password());
  }

  @Test
  public void selectOneByUsername() {
    var username = UUID.randomUUID().toString();
    repository.insertOne(username, UUID.randomUUID().toString());
    var found = repository.selectOneByUsername(username);
    assertTrue(found.isDefined());
  }
}
