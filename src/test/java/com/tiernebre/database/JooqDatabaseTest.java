package com.tiernebre.database;

import org.jooq.DSLContext;

public abstract class JooqDatabaseTest {

  protected static final TestDatabaseContext context =
    TestDatabaseContextFactory.create();
  protected static final DSLContext dsl = context.dsl();
}
