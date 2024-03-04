package com.tiernebre.web.routes.api;

import static io.javalin.apibuilder.ApiBuilder.*;

import io.javalin.apibuilder.EndpointGroup;

public final class ApiRoutes implements EndpointGroup {

  private final AuthenticationRoutes authenticationRoutes;
  private final HealthRoutes healthRoutes;

  public ApiRoutes(
    AuthenticationRoutes authenticationRoutes,
    HealthRoutes healthRoutes
  ) {
    this.authenticationRoutes = authenticationRoutes;
    this.healthRoutes = healthRoutes;
  }

  @Override
  public void addEndpoints() {
    path("/api", () -> {
      authenticationRoutes.addEndpoints();
      healthRoutes.addEndpoints();
    });
  }
}
