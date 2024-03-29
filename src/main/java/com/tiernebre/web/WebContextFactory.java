package com.tiernebre.web;

import com.tiernebre.authentication.AuthenticationContext;
import com.tiernebre.context.UtilityContext;
import com.tiernebre.util.pagination.DefaultWebPaginationHelper;
import com.tiernebre.web.util.CookieSessionRegistry;
import com.tiernebre.web.util.DefaultWebHelper;

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
    var sessionRegistry = new CookieSessionRegistry(
      authenticationContext.sessionService(),
      utilityContext.clock()
    );
    return new WebContext(
      sessionRegistry,
      new DefaultWebHelper(sessionRegistry, new DefaultWebPaginationHelper())
    );
  }
}
