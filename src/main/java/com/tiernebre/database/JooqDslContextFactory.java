package com.tiernebre.database;

import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

public final class JooqDslContextFactory {

  private final DatabaseConnectionFactory databaseConnectionFactory;

  public JooqDslContextFactory(
    DatabaseConnectionFactory databaseConnectionFactory
  ) {
    this.databaseConnectionFactory = databaseConnectionFactory;
  }

  public DSLContext create() throws SQLException {
    return DSL.using(databaseConnectionFactory.create());
  }
}
