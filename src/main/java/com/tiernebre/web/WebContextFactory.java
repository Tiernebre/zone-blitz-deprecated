package com.tiernebre.web;

import com.tiernebre.authentication.AuthenticationContext;
import com.tiernebre.web.util.CookieSessionRegistry;

public final class WebContextFactory {

  private final AuthenticationContext authenticationContext;

  public WebContextFactory(AuthenticationContext authenticationContext) {
    this.authenticationContext = authenticationContext;
  }

  public WebContext create() {
    return new WebContext(
      new CookieSessionRegistry(authenticationContext.sessionService())
    );
  }
}
