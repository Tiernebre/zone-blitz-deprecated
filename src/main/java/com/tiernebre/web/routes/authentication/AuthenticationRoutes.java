package com.tiernebre.web.routes.authentication;

import io.javalin.apibuilder.EndpointGroup;

public final class AuthenticationRoutes implements EndpointGroup {

  private final LoginRoutes loginRoutes;
  private final LogoutRoutes logoutRoutes;
  private final RegistrationRoutes registrationRoutes;

  public AuthenticationRoutes(
    LoginRoutes loginRoutes,
    LogoutRoutes logoutRoutes,
    RegistrationRoutes registrationRoutes
  ) {
    this.loginRoutes = loginRoutes;
    this.logoutRoutes = logoutRoutes;
    this.registrationRoutes = registrationRoutes;
  }

  @Override
  public void addEndpoints() {
    loginRoutes.addEndpoints();
    logoutRoutes.addEndpoints();
    registrationRoutes.addEndpoints();
  }
}
