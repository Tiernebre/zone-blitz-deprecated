package com.tiernebre.database;

import com.tiernebre.util.pagination.Base64CursorMapper;
import com.tiernebre.util.pagination.CursorMapper;
import java.util.Base64;
import org.jooq.DSLContext;

public abstract class JooqDatabaseTest {

  protected static final TestDatabaseContext context =
    TestDatabaseContextFactory.create();
  protected static final DSLContext dsl = context.dsl();
  protected static final CursorMapper cursorMapper = new Base64CursorMapper(
    Base64.getEncoder(),
    Base64.getDecoder()
  );
}
