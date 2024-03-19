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
    var inserted = repository.insertOne(username, password).get();
    assertNotNull(inserted.id());
    assertEquals(username, inserted.username());
    assertEquals(password, inserted.password());
  }

  @Test
  public void insertOneFailure() {
    var result = repository.insertOne(null, "");
    assertTrue(result.isLeft());
  }

  @Test
  public void selectOneByUsername() {
    var username = UUID.randomUUID().toString();
    var inserted = repository
      .insertOne(username, UUID.randomUUID().toString())
      .get();
    var found = repository.selectOneByUsername(username);
    assertTrue(found.isDefined());
    assertEquals(found.get().username(), inserted.username());
    assertEquals(inserted.password(), found.get().password());
  }
}
