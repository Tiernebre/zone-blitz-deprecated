package com.tiernebre.database;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import org.junit.Test;

public final class JooqDslContextFactoryIntegrationTest {

  @Test
  public void connectsToDatabase() throws SQLException {
    var results = new JooqDslContextFactory()
      .create()
      .resultQuery("SELECT 1")
      .fetch();
    assertEquals(1, results.size());
    assertEquals(1, results.get(0).getValue(0));
  }
}
