package com.tiernebre.database;

public final class DatabaseContextFactory {

  public DatabaseContext create() throws DatabaseConnectionError {
    return new DatabaseContext(
      new JooqDslContextFactory(
        new DatabaseConnectionFactory(DatabaseConstants.CONFIGURATION)
      ).create()
    );
  }
}
