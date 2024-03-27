package com.tiernebre.web.routes;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.tiernebre.web.controllers.IndexController;
import io.javalin.apibuilder.EndpointGroup;

public class PageRoutes implements EndpointGroup {

  private final IndexController indexController;

  public PageRoutes(IndexController indexController) {
    this.indexController = indexController;
  }

  @Override
  public void addEndpoints() {
    get("", indexController::render);
  }
}
