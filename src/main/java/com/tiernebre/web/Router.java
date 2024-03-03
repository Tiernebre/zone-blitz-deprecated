package com.tiernebre.web;

import com.tiernebre.web.controllers.AuthenticationController;
import com.tiernebre.web.controllers.FrontPageController;
import com.tiernebre.web.controllers.HealthController;
import com.tiernebre.web.controllers.LoginPageController;
import io.javalin.Javalin;

public final class Router {

  private final FrontPageController frontPageController;
  private final HealthController healthController;
  private final LoginPageController loginPageController;
  private final AuthenticationController authenticationController;

  public Router(
    FrontPageController frontPageController,
    HealthController healthController,
    LoginPageController loginPageController,
    AuthenticationController authenticationController
  ) {
    this.frontPageController = frontPageController;
    this.healthController = healthController;
    this.loginPageController = loginPageController;
    this.authenticationController = authenticationController;
  }

  public Javalin register(Javalin app) {
    return app
      .get("/", frontPageController::render)
      .get("/login", loginPageController::render)
      .get("/api/health", healthController::health)
      .post(
        "/api/authenticate/google",
        authenticationController::handleGoogleSignOn
      );
  }
}
