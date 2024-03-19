package com.tiernebre.web.routes;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.tiernebre.web.controllers.authentication.LoginController;
import io.javalin.apibuilder.EndpointGroup;

public final class LoginRoutes implements EndpointGroup {

  private final String URI = "login";

  private final LoginController controller;

  public LoginRoutes(LoginController controller) {
    this.controller = controller;
  }

  @Override
  public void addEndpoints() {
    get(URI, controller::render);
    post(URI, controller::handle);
  }
}
