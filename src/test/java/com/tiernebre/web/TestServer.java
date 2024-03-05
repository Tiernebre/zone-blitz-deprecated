package com.tiernebre.web;

import com.tiernebre.context.DependencyContextFactory;
import com.tiernebre.database.DatabaseConnectionError;
import io.javalin.Javalin;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Test server for functional and integration tests of the Javalin web server layer.
 *
 * See functional and integration testing docs at https://javalin.io/tutorials/testing.
 */
public final class TestServer {

  private static Javalin SERVER = null;

  public static Javalin create() {
    if (SERVER == null) {
      try {
        SERVER = new ServerFactory(new DependencyContextFactory().create())
          .create()
          .create();
      } catch (
        GeneralSecurityException | IOException | DatabaseConnectionError e
      ) {
        throw new RuntimeException(
          String.format("Could not create TestServer, caught error %s", e)
        );
      }
    }
    return SERVER;
  }
}
