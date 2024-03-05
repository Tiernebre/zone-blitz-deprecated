package com.tiernebre.web.api;

import static org.junit.Assert.assertEquals;

import com.tiernebre.web.TestServer;
import io.javalin.testtools.JavalinTest;
import org.junit.Test;

public class HealthApiTest {

  @Test
  public void GET_healthy() {
    JavalinTest.test(TestServer.create(), (server, client) -> {
      var response = client.get("/api/health");
      assertEquals(200, response.code());
    });
  }
}
