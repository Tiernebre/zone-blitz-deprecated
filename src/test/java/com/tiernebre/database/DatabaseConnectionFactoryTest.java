package com.tiernebre.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.SQLException;
import org.junit.Test;

public final class DatabaseConnectionFactoryTest {

  @Test
  public void createsAWorkingConnection() throws SQLException {
    var connection = new DatabaseConnectionFactory().create();
    assertFalse(connection.isClosed());
    var result = connection.createStatement().executeQuery("SELECT 1 as test");
    assertEquals(1, result.findColumn("test"));
  }
}
