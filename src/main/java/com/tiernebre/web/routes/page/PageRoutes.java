package com.tiernebre.web.routes.page;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.tiernebre.web.controllers.FrontPageController;
import com.tiernebre.web.controllers.LoginPageController;
import com.tiernebre.web.controllers.authentication.RegistrationController;
import io.javalin.apibuilder.EndpointGroup;

public class PageRoutes implements EndpointGroup {

  private final FrontPageController frontPageController;
  private final LoginPageController loginPageController;
  private final RegistrationController registrationController;

  public PageRoutes(
    FrontPageController frontPageController,
    LoginPageController loginPageController,
    RegistrationController registrationController
  ) {
    this.frontPageController = frontPageController;
    this.loginPageController = loginPageController;
    this.registrationController = registrationController;
  }

  @Override
  public void addEndpoints() {
    get("", frontPageController::render);
    get("login", loginPageController::render);
    get("registration", registrationController::page);
    post("registration", registrationController::create);
  }
}
