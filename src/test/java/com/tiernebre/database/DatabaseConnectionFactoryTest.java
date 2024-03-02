package com.tiernebre.database;

import static org.junit.Assert.assertFalse;

import java.sql.SQLException;
import org.junit.Test;

public final class DatabaseConnectionFactoryTest {

  @Test
  public void createsAWorkingConnection() throws SQLException {
    var connection = new DatabaseConnectionFactory().create();
    assertFalse(connection.isClosed());
  }
}
