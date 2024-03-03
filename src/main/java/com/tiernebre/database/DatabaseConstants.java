package com.tiernebre.database;

public final class DatabaseConstants {

  public static final DatabaseConfiguration CONFIGURATION =
    new DatabaseConfiguration(
      System.getenv("ZONE_BLITZ_POSTGRES_USER"),
      System.getenv("ZONE_BLITZ_POSTGRES_PASSWORD"),
      System.getenv("ZONE_BLITZ_POSTGRES_JDBC_URL")
    );
}
