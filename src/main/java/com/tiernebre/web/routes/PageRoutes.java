package com.tiernebre.web.routes;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.tiernebre.web.controllers.IndexController;
import io.javalin.apibuilder.EndpointGroup;

public class PageRoutes implements EndpointGroup {

  private final IndexController indexController;
  private final RegistrationRoutes registrationRoutes;
  private final LoginRoutes loginRoutes;
  private final LogoutRoutes logoutRoutes;

  public PageRoutes(
    IndexController frontPageController,
    RegistrationRoutes registrationRoutes,
    LoginRoutes loginRoutes,
    LogoutRoutes logoutRoutes
  ) {
    this.indexController = frontPageController;
    this.registrationRoutes = registrationRoutes;
    this.loginRoutes = loginRoutes;
    this.logoutRoutes = logoutRoutes;
  }

  @Override
  public void addEndpoints() {
    get("", indexController::render);
    registrationRoutes.addEndpoints();
    loginRoutes.addEndpoints();
    logoutRoutes.addEndpoints();
  }
}
