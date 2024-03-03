package com.tiernebre.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DatabaseConnectionFactory {

  private final DatabaseConfiguration configuration;
  private static final Logger LOG = LoggerFactory.getLogger(
    DatabaseConnectionFactory.class
  );

  public DatabaseConnectionFactory(DatabaseConfiguration configuration) {
    this.configuration = configuration;
  }

  public Connection create() throws DatabaseConnectionError {
    try {
      return DriverManager.getConnection(
        configuration.url(),
        configuration.user(),
        configuration.password()
      );
    } catch (SQLException e) {
      String error = String.format(
        "Could not connect to the PostgreSQL database. Received exception %s",
        e
      );
      LOG.error(error);
      throw new DatabaseConnectionError(error);
    }
  }
}
