package com.tiernebre.web.routes.page;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.tiernebre.web.controllers.IndexController;
import com.tiernebre.web.controllers.authentication.LoginController;
import com.tiernebre.web.routes.RegistrationRoutes;
import io.javalin.apibuilder.EndpointGroup;

public class PageRoutes implements EndpointGroup {

  private final IndexController indexController;
  private final LoginController loginController;
  private final RegistrationRoutes registrationRoutes;

  public PageRoutes(
    IndexController frontPageController,
    LoginController loginPageController,
    RegistrationRoutes registrationRoutes
  ) {
    this.indexController = frontPageController;
    this.loginController = loginPageController;
    this.registrationRoutes = registrationRoutes;
  }

  @Override
  public void addEndpoints() {
    get("", indexController::render);
    get("login", loginController::render);
    this.registrationRoutes.addEndpoints();
  }
}
