package com.tiernebre.web;

import com.tiernebre.context.DependencyContextFactory;
import io.javalin.Javalin;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.util.Map;

public final class WebHttpTestUtils {

  private static Javalin SERVER = null;
  private static HttpClient CLIENT = null;

  public static Javalin startServer() {
    if (SERVER == null) {
      try {
        SERVER = new ServerFactory(new DependencyContextFactory().create())
          .create()
          .start(49152);
      } catch (Exception e) {
        throw new RuntimeException(
          String.format("Could not create TestServer, caught error %s", e)
        );
      }
    }
    return SERVER;
  }

  public static HttpClient client() {
    if (CLIENT == null) {
      CLIENT = HttpClient.newBuilder()
        .version(Version.HTTP_2)
        .followRedirects(Redirect.NORMAL)
        .build();
    }
    return CLIENT;
  }

  public static URI url(String path) {
    return URI.create(
      String.format("http://0.0.0.0:%s%s", SERVER.port(), path)
    );
  }
}
