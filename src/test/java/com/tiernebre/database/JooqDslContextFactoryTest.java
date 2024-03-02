package com.tiernebre.database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class JooqDslContextFactoryTest {

  @Test
  public void connectsToDatabase() {
    var results = new JooqDslContextFactory(
      new DatabaseConnectionFactory(Constants.CONFIGURATION)
    )
      .create()
      .resultQuery("SELECT 1")
      .fetch();
    assertEquals(1, results.size());
    assertEquals(1, results.get(0).getValue(0));
  }
}
