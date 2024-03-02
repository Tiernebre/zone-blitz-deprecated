package com.tiernebre.database;

import com.tiernebre.AppStatusCodes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

public final class DatabaseConnectionFactory {

  public static final Logger logger = LoggerFactory.getLogger(
    DatabaseConnectionFactory.class
  );

  public Connection create() {
    try {
      return DriverManager.getConnection(
        System.getenv("ZONE_BLITZ_POSTGRES_JDBC_URL"),
        System.getenv("ZONE_BLITZ_POSTGRES_USER"),
        System.getenv("ZONE_BLITZ_POSTGRES_PASSWORD")
      );
    } catch (SQLException e) {
      logger.error(
        MarkerFactory.getMarker("FATAL"),
        String.format(
          "Could not connect to the PostgreSQL database. Received exception %s",
          e
        )
      );
      System.exit(AppStatusCodes.FAILED_TO_CONNECT_TO_DATABASE_ERROR);
      return null;
    }
  }
}
