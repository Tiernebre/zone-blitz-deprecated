package com.tiernebre.web;

import com.tiernebre.web.middlewares.Middlewares;
import com.tiernebre.web.routes.Routes;
import io.javalin.Javalin;

public final class Server {

  private final Routes routes;
  private final Middlewares middlewares;

  public Server(Routes routes, Middlewares middlewares) {
    this.routes = routes;
    this.middlewares = middlewares;
  }

  public Javalin start() {
    return start(8000);
  }

  public Javalin start(int port) {
    return middlewares
      .register(
        Javalin.create(config -> {
          config.showJavalinBanner = false;
          config.staticFiles.add(staticFiles -> {
            staticFiles.hostedPath = "/";
            staticFiles.directory = "/assets";
          });
          config.router.apiBuilder(routes::addEndpoints);
        })
      )
      .start("0.0.0.0", port);
  }
}
