package com.tiernebre.web.middlewares;

import com.tiernebre.context.DependencyContext;

public final class MiddlewaresFactory {

  private final DependencyContext dependencyContext;

  public MiddlewaresFactory(DependencyContext dependencyContext) {
    this.dependencyContext = dependencyContext;
  }

  public Middlewares create() {
    return new Middlewares(
      new SessionMiddleware(
        dependencyContext.authentication().sessionService()
      ),
      new SecurityMiddleware()
    );
  }
}
