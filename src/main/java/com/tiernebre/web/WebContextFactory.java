package com.tiernebre.web;

import com.tiernebre.authentication.AuthenticationContext;
import com.tiernebre.context.UtilityContext;
import com.tiernebre.web.util.CookieSessionRegistry;

public final class WebContextFactory {

  private final AuthenticationContext authenticationContext;
  private final UtilityContext utilityContext;

  public WebContextFactory(
    AuthenticationContext authenticationContext,
    UtilityContext utilityContext
  ) {
    this.authenticationContext = authenticationContext;
    this.utilityContext = utilityContext;
  }

  public WebContext create() {
    return new WebContext(
      new CookieSessionRegistry(
        authenticationContext.sessionService(),
        utilityContext.clock()
      )
    );
  }
}
