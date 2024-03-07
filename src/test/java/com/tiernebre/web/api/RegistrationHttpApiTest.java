package com.tiernebre.web.api;

import static org.junit.Assert.assertEquals;

import com.tiernebre.web.UrlEncodedFormBodyPublisher;
import com.tiernebre.web.WebHttpTestUtils;
import io.javalin.Javalin;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import org.apache.http.entity.ContentType;
import org.junit.Test;

public class RegistrationHttpApiTest {

  Javalin server = WebHttpTestUtils.startServer();

  @Test
  public void POST_registration() throws IOException, InterruptedException {
    var response = WebHttpTestUtils.client()
      .send(
        HttpRequest.newBuilder(WebHttpTestUtils.url("/api/registration"))
          .header(
            "Content-Type",
            ContentType.APPLICATION_FORM_URLENCODED.toString()
          )
          .POST(
            UrlEncodedFormBodyPublisher.ofParams(
              Map.of("username", "username", "password", "password")
            )
          )
          .build(),
        BodyHandlers.ofLines()
      );
    assertEquals(201, response.statusCode());
  }
}
