package com.tiernebre.database;

public final class DatabaseConnectionError extends RuntimeException {

  public DatabaseConnectionError(String message) {
    super(message);
  }
}
