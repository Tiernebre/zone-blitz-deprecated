package com.tiernebre.web.routers.page;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.tiernebre.web.controllers.FrontPageController;
import com.tiernebre.web.controllers.LoginPageController;
import io.javalin.apibuilder.EndpointGroup;

public class PageRoutes implements EndpointGroup {

  private final FrontPageController frontPageController;
  private final LoginPageController loginPageController;

  public PageRoutes(
    FrontPageController frontPageController,
    LoginPageController loginPageController
  ) {
    this.frontPageController = frontPageController;
    this.loginPageController = loginPageController;
  }

  @Override
  public void addEndpoints() {
    get("/", frontPageController::render);
    get("/login", loginPageController::render);
  }
}
