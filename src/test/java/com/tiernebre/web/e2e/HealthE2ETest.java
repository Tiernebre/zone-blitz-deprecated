package com.tiernebre.web.e2e;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HealthE2ETest extends E2EHttpSuite {

  @Test
  public void apiHealth() {
    var request = BROWSER.newContext().request();
    var response = request.get(url("/api/health"));
    assertEquals("Healthy", new String(response.body()));
  }
}
