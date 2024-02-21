package com.tiernebre.web;

import io.javalin.Javalin;

public final class Server {

  private final Router router;

  public Server(Router router) {
    this.router = router;
  }

  public Javalin start() {
    return router
      .register(
        Javalin.create(config -> {
          config.staticFiles.add(staticFiles -> {
            staticFiles.hostedPath = "/assets";
            staticFiles.directory = "/assets";
          });
        })
      )
      .start(8000);
  }
}
