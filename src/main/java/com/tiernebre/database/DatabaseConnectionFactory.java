package com.tiernebre.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnectionFactory {

  public Connection create() throws SQLException {
    return DriverManager.getConnection(
      System.getenv("ZONE_BLITZ_POSTGRES_JDBC_URL"),
      System.getenv("ZONE_BLITZ_POSTGRES_USER"),
      System.getenv("ZONE_BLITZ_POSTGRES_PASSWORD")
    );
  }
}
