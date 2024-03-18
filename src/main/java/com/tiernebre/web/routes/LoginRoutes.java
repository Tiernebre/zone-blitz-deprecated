package com.tiernebre.web.routes;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.tiernebre.web.controllers.authentication.AuthenticationController;
import io.javalin.apibuilder.EndpointGroup;

public final class LoginRoutes implements EndpointGroup {

  private final AuthenticationController controller;

  public LoginRoutes(AuthenticationController controller) {
    this.controller = controller;
  }

  @Override
  public void addEndpoints() {
    post("login", controller);
  }
}
