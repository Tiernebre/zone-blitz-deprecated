package com.tiernebre.web.routes;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

import com.tiernebre.web.controllers.RegistrationPageController;
import com.tiernebre.web.controllers.authentication.RegistrationController;
import io.javalin.apibuilder.EndpointGroup;

public final class RegistrationRoutes implements EndpointGroup {

  private final RegistrationController controller;
  private final RegistrationPageController pageController;

  public RegistrationRoutes(
    RegistrationController controller,
    RegistrationPageController pageController
  ) {
    this.controller = controller;
    this.pageController = pageController;
  }

  @Override
  public void addEndpoints() {
    get("registration", pageController::render);
    post("registration", controller::create);
  }
}
