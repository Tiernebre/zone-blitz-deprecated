package com.tiernebre.web.routes.api;

import static io.javalin.apibuilder.ApiBuilder.*;

import io.javalin.apibuilder.EndpointGroup;

public final class ApiRoutes implements EndpointGroup {

  private final HealthRoutes healthRoutes;

  public ApiRoutes(HealthRoutes healthRoutes) {
    this.healthRoutes = healthRoutes;
  }

  @Override
  public void addEndpoints() {
    path("/api", () -> {
      healthRoutes.addEndpoints();
    });
  }
}
