package com.tiernebre.web.routes.authentication;

import static io.javalin.apibuilder.ApiBuilder.post;

import com.tiernebre.web.controllers.authentication.LogoutController;
import io.javalin.apibuilder.EndpointGroup;

public final class LogoutRoutes implements EndpointGroup {

  private final LogoutController controller;

  public LogoutRoutes(LogoutController controller) {
    this.controller = controller;
  }

  @Override
  public void addEndpoints() {
    post("/logout", controller);
  }
}
