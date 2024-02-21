package com.tiernebre.web;

public final class ServerFactory {

  public Server create() {
    return new Server(new RouterFactory().create());
  }
}
