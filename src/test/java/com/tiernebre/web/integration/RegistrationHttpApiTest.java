package com.tiernebre.web.integration;

import static org.junit.Assert.assertEquals;

import com.tiernebre.web.UrlEncodedFormBodyPublisher;
import com.tiernebre.web.WebHttpTestUtils;
import com.tiernebre.web.constants.WebConstants;
import io.javalin.Javalin;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import java.util.UUID;
import org.apache.http.entity.ContentType;
import org.junit.Test;

public class RegistrationHttpApiTest {

  Javalin server = WebHttpTestUtils.startServer();

  @Test
  public void POST_registration_empty_body()
    throws IOException, InterruptedException {
    var response = WebHttpTestUtils.client()
      .send(
        HttpRequest.newBuilder(WebHttpTestUtils.url("/api/registration"))
          .header(
            "Content-Type",
            ContentType.APPLICATION_FORM_URLENCODED.toString()
          )
          .POST(UrlEncodedFormBodyPublisher.ofParams(Map.of()))
          .build(),
        BodyHandlers.ofLines()
      );
    assertEquals(400, response.statusCode());
  }

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
              Map.of(
                WebConstants.Authentication.REGISTRATION_USERNAME_PARAM,
                UUID.randomUUID().toString(),
                WebConstants.Authentication.REGISTRATION_PASSWORD_PARAM,
                "password",
                WebConstants.Authentication.REGISTRATION_CONFIRM_PASSWORD_PARAM,
                "password"
              )
            )
          )
          .build(),
        BodyHandlers.ofLines()
      );
    assertEquals(201, response.statusCode());
  }
}
