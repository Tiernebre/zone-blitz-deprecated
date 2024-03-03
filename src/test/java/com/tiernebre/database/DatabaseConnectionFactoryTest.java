package com.tiernebre.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.SQLException;
import org.junit.Test;

public final class DatabaseConnectionFactoryTest {

  @Test
  public void createsAWorkingConnection()
    throws SQLException, DatabaseConnectionError {
    var connection = new DatabaseConnectionFactory(
      DatabaseConstants.CONFIGURATION
    ).create();
    assertFalse(connection.isClosed());
    var result = connection.createStatement().executeQuery("SELECT 1 as test");
    assertEquals(1, result.findColumn("test"));
  }

  @Test(expected = DatabaseConnectionError.class)
  public void failsFastIfConnectionInvalid()
    throws SQLException, DatabaseConnectionError {
    new DatabaseConnectionFactory(
      new DatabaseConfiguration(
        "notauser",
        "notapassword",
        "jdbc:postgresql://0.0.0.0:5432/not_a_db"
      )
    ).create();
  }
}
