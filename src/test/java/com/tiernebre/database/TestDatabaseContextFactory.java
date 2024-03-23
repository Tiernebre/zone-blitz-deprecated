package com.tiernebre.database;

import com.tiernebre.authentication.account.JooqAccountRepository;
import org.jooq.DSLContext;

public final class TestDatabaseContextFactory {

  public static TestDatabaseContext create() {
    return new TestDatabaseContext(
      createTestDSLContext(),
      new JooqAccountRepository(createTestDSLContext())
    );
  }

  public static DSLContext createTestDSLContext() {
    try {
      return new JooqDslContextFactory(
        new DatabaseConnectionFactory(DatabaseConstants.CONFIGURATION)
      ).create();
    } catch (DatabaseConnectionError e) {
      return null;
    }
  }
}
