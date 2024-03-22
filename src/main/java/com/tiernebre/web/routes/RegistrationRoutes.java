package com.tiernebre.web.routes;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

import com.tiernebre.web.controllers.authentication.RegistrationController;
import io.javalin.apibuilder.EndpointGroup;

public final class RegistrationRoutes implements EndpointGroup {

  private final RegistrationController controller;

  public RegistrationRoutes(RegistrationController controller) {
    this.controller = controller;
  }

  @Override
  public void addEndpoints() {
    get("registration", controller::render);
    post("registration", controller::submit);
  }
}
