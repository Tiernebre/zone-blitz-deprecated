package com.tiernebre.web;

import com.tiernebre.web.controllers.FrontPageController;
import com.tiernebre.web.controllers.HealthController;
import io.javalin.Javalin;

public final class Router {

  private final FrontPageController frontPageController;
  private final HealthController healthController;

  public Router(
    FrontPageController frontPageController,
    HealthController healthController
  ) {
    this.frontPageController = frontPageController;
    this.healthController = healthController;
  }

  public Javalin register(Javalin app) {
    return app
      .get("/", frontPageController::render)
      .get("/api/health", healthController::health);
  }
}
