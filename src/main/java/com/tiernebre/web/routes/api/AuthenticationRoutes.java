package com.tiernebre.web.routes.api;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.tiernebre.web.controllers.authentication.AuthenticationController;
import io.javalin.apibuilder.EndpointGroup;

public final class AuthenticationRoutes implements EndpointGroup {

  private final AuthenticationController controller;

  public AuthenticationRoutes(AuthenticationController controller) {
    this.controller = controller;
  }

  @Override
  public void addEndpoints() {
    post("authenticate", controller::handle);
  }
}
