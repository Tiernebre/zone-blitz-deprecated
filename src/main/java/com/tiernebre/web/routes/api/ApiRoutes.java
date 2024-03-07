package com.tiernebre.web.routes.api;

import static io.javalin.apibuilder.ApiBuilder.*;

import io.javalin.apibuilder.EndpointGroup;

public final class ApiRoutes implements EndpointGroup {

  private final AuthenticationRoutes authenticationRoutes;
  private final HealthRoutes healthRoutes;
  private final RegistrationRoutes registrationRoutes;

  public ApiRoutes(
    AuthenticationRoutes authenticationRoutes,
    HealthRoutes healthRoutes,
    RegistrationRoutes registrationRoutes
  ) {
    this.authenticationRoutes = authenticationRoutes;
    this.healthRoutes = healthRoutes;
    this.registrationRoutes = registrationRoutes;
  }

  @Override
  public void addEndpoints() {
    path("/api", () -> {
      authenticationRoutes.addEndpoints();
      healthRoutes.addEndpoints();
      registrationRoutes.addEndpoints();
    });
  }
}
