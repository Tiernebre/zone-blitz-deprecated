package com.tiernebre.web;

import com.tiernebre.web.controllers.FrontPageController;
import io.javalin.Javalin;

public final class Router {

  private final FrontPageController frontPageController;

  public Router(FrontPageController frontPageController) {
    this.frontPageController = frontPageController;
  }

  public Javalin register(Javalin app) {
    return app.get("/", frontPageController::render);
  }
}
