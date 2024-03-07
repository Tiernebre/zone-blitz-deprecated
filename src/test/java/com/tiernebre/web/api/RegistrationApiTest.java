package com.tiernebre.web.api;

import static org.junit.Assert.assertEquals;

import com.tiernebre.web.TestServer;
import io.javalin.testtools.JavalinTest;
import org.junit.Test;

public class RegistrationApiTest {

  @Test
  public void POST_no_form() {
    JavalinTest.test(TestServer.create(), (server, client) -> {
      var response = client.post("/api/registration");
      assertEquals(400, response.code());
    });
  }
}
