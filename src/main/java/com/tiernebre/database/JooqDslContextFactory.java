package com.tiernebre.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

public class JooqDslContextFactory {

  public DSLContext create() throws SQLException {
    return DSL.using(
      DriverManager.getConnection(
        System.getenv("ZONE_BLITZ_POSTGRES_JDBC_URL"),
        System.getenv("ZONE_BLITZ_POSTGRES_USER"),
        System.getenv("ZONE_BLITZ_POSTGRES_PASSWORD")
      )
    );
  }
}
