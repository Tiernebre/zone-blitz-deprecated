package com.tiernebre.database;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

public final class JooqDslContextFactory {

  private final DatabaseConnectionFactory databaseConnectionFactory;

  public JooqDslContextFactory(
    DatabaseConnectionFactory databaseConnectionFactory
  ) {
    this.databaseConnectionFactory = databaseConnectionFactory;
  }

  public DSLContext create() {
    return DSL.using(databaseConnectionFactory.create());
  }
}
