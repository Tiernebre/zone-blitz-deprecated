package com.tiernebre.database;

import org.jooq.DSLContext;

public class TestJooqDslContextFactory {

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
