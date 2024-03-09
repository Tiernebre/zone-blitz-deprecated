package com.tiernebre.authentication.registration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.tiernebre.database.TestJooqDslContextFactory;
import java.util.Arrays;
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
    var inserted = repository.insertOne(username, password.getBytes()).get();
    assertNotNull(inserted.id());
    assertEquals(username, inserted.username());
    assertTrue(Arrays.equals(password.getBytes(), inserted.password()));
  }

  @Test
  public void insertOneFailure() {
    var result = repository.insertOne(null, new byte[] {});
    assertTrue(result.isLeft());
  }

  @Test
  public void selectOneByUsername() {
    var username = UUID.randomUUID().toString();
    var inserted = repository
      .insertOne(username, UUID.randomUUID().toString().getBytes())
      .get();
    var found = repository.selectOneByUsername(username);
    assertTrue(found.isDefined());
    assertEquals(found.get().username(), inserted.username());
    assertTrue(Arrays.equals(found.get().password(), inserted.password()));
  }
}
