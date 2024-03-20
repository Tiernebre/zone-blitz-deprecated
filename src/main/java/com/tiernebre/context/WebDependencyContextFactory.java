package com.tiernebre.context;

import com.tiernebre.authentication.AuthenticationContext;
import com.tiernebre.web.controllers.authentication.CookieSessionRegistry;

public final class WebDependencyContextFactory {

  private final AuthenticationContext authenticationContext;

  public WebDependencyContextFactory(
    AuthenticationContext authenticationContext
  ) {
    this.authenticationContext = authenticationContext;
  }

  public WebDependencyContext create() {
    return new WebDependencyContext(
      new CookieSessionRegistry(authenticationContext.sessionService())
    );
  }
}
