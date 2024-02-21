package com.tiernebre.web;

import com.tiernebre.web.controllers.FrontPageController;

public final class RouterFactory {

  public Router create() {
    return new Router(new FrontPageController());
  }
}
