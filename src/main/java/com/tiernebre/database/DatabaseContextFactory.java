package com.tiernebre.database;

import com.tiernebre.util.pagination.Base64CursorMapper;
import java.util.Base64;

public final class DatabaseContextFactory {

  public DatabaseContext create() throws DatabaseConnectionError {
    var dslContext = new JooqDslContextFactory(
      new DatabaseConnectionFactory(DatabaseConstants.CONFIGURATION)
    ).create();
    return new DatabaseContext(
      dslContext,
      new JooqRepositoryPaginationStrategy(
        dslContext,
        new Base64CursorMapper(Base64.getEncoder(), Base64.getDecoder())
      )
    );
  }
}
