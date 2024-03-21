package com.tiernebre.web.middlewares;

import com.tiernebre.context.DependencyContext;

public final class MiddlewaresFactory {

  private final DependencyContext dependencyContext;

  public MiddlewaresFactory(DependencyContext dependencyContext) {
    this.dependencyContext = dependencyContext;
  }

  public Middlewares create() {
    var web = dependencyContext.web();
    return new Middlewares(
      new SessionParserMiddleware(web.sessionRegistry()),
      new SecurityMiddleware(),
      new SessionRefresherMiddleware(web.sessionRegistry())
    );
  }
}
