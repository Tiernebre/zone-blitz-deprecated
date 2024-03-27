package com.tiernebre.web.guards;

import com.tiernebre.context.DependencyContext;

public final class GuardsFactory {

  private final DependencyContext dependencyContext;

  public GuardsFactory(DependencyContext dependencyContext) {
    this.dependencyContext = dependencyContext;
  }

  public Guards create() {
    return new Guards(
      new AuthenticationGuard(dependencyContext.web().helper())
    );
  }
}
