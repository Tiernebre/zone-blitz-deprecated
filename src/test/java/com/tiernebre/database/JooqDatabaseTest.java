package com.tiernebre.database;

import com.tiernebre.database.jooq.Tables;
import com.tiernebre.util.pagination.Base64CursorMapper;
import com.tiernebre.util.pagination.CursorMapper;
import java.util.Base64;
import java.util.List;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;

public abstract class JooqDatabaseTest {

  protected static final TestDatabaseContext context =
    TestDatabaseContextFactory.create();
  protected static final DSLContext dsl = context.dsl();
  protected static final CursorMapper cursorMapper = new Base64CursorMapper(
    Base64.getEncoder(),
    Base64.getDecoder()
  );
  protected static final JooqRepositoryPaginationStrategy paginationStrategy =
    new JooqRepositoryPaginationStrategy(dsl, cursorMapper);

  @BeforeEach
  public void beforeEach() {
    List.of(
      Tables.LEAGUE,
      Tables.PLAYER,
      Tables.SESSION,
      Tables.ACCOUNT,
      Tables.REGISTRATION
    ).forEach(table -> {
      dsl.delete(table).execute();
    });
  }
}
