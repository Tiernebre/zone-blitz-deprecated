package com.tiernebre.web;

import com.tiernebre.web.routes.Routes;
import io.javalin.Javalin;

public final class Server {

  private final Routes routes;

  public Server(Routes routes) {
    this.routes = routes;
  }

  public Javalin start() {
    return Javalin.create(config -> {
      config.showJavalinBanner = false;
      config.staticFiles.add(staticFiles -> {
        staticFiles.hostedPath = "/";
        staticFiles.directory = "/assets";
      });
      config.router.apiBuilder(routes::addEndpoints);
    }).start("0.0.0.0", 8000);
  }
}
