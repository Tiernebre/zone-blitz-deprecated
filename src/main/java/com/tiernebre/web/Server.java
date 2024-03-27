package com.tiernebre.web;

import com.tiernebre.web.errors.Errors;
import com.tiernebre.web.guards.Guards;
import com.tiernebre.web.middlewares.Middlewares;
import com.tiernebre.web.routes.Routes;
import io.javalin.Javalin;

public final class Server {

  private final Routes routes;
  private final Middlewares middlewares;
  private final Guards guards;
  private final Errors errors;

  public Server(
    Routes routes,
    Middlewares middlewares,
    Guards guards,
    Errors errors
  ) {
    this.routes = routes;
    this.middlewares = middlewares;
    this.guards = guards;
    this.errors = errors;
  }

  public Javalin start() {
    return start(8000);
  }

  public Javalin start(int port) {
    var server = Javalin.create(config -> {
      config.showJavalinBanner = false;
      config.staticFiles.add(staticFiles -> {
        staticFiles.hostedPath = "/";
        staticFiles.directory = "/assets";
      });
      config.router.apiBuilder(routes::addEndpoints);
    });
    middlewares.register(server);
    guards.register(server);
    errors.register(server);
    return server.start("0.0.0.0", port);
  }
}
