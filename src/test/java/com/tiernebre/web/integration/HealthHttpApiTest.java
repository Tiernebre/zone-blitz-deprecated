package com.tiernebre.web.integration;

import static org.junit.Assert.assertEquals;

import com.tiernebre.web.WebHttpTestUtils;
import io.javalin.Javalin;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import org.junit.Test;

public class HealthHttpApiTest {

  Javalin server = WebHttpTestUtils.startServer();

  @Test
  public void GET_healthy() throws IOException, InterruptedException {
    var response = WebHttpTestUtils.client()
      .send(
        HttpRequest.newBuilder(WebHttpTestUtils.url("/api/health"))
          .GET()
          .build(),
        BodyHandlers.ofLines()
      );
    assertEquals(200, response.statusCode());
  }
}
