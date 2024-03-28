package com.tiernebre.web.errors;

import com.tiernebre.context.DependencyContext;

public final class ErrorsFactory {

  private final DependencyContext dependencyContext;

  public ErrorsFactory(DependencyContext dependencyContext) {
    this.dependencyContext = dependencyContext;
  }

  public Errors create() {
    var helper = dependencyContext.web().helper();
    return new Errors(new CatchAllHandler(helper), new NotFoundHandler(helper));
  }
}
