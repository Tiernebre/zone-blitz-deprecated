package com.tiernebre.web.routers.api;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.tiernebre.web.controllers.HealthController;
import io.javalin.apibuilder.EndpointGroup;

public final class HealthRoutes implements EndpointGroup {

  private final HealthController health;

  public HealthRoutes(HealthController healthController) {
    this.health = healthController;
  }

  @Override
  public void addEndpoints() {
    get("health", health::health);
  }
}
