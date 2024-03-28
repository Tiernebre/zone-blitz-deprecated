package com.tiernebre.web.errors;

public final class ErrorsFactory {

  public Errors create() {
    return new Errors(new CatchAllHandler(), new NotFoundHandler());
  }
}
