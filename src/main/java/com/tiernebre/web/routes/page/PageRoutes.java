package com.tiernebre.web.routes.page;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.tiernebre.web.controllers.FrontPageController;
import com.tiernebre.web.controllers.LoginPageController;
import com.tiernebre.web.routes.RegistrationRoutes;
import io.javalin.apibuilder.EndpointGroup;

public class PageRoutes implements EndpointGroup {

  private final FrontPageController frontPageController;
  private final LoginPageController loginPageController;
  private final RegistrationRoutes registrationRoutes;

  public PageRoutes(
    FrontPageController frontPageController,
    LoginPageController loginPageController,
    RegistrationRoutes registrationRoutes
  ) {
    this.frontPageController = frontPageController;
    this.loginPageController = loginPageController;
    this.registrationRoutes = registrationRoutes;
  }

  @Override
  public void addEndpoints() {
    get("", frontPageController::render);
    get("login", loginPageController::render);
    this.registrationRoutes.addEndpoints();
  }
}
