package com.tiernebre.web;

import com.tiernebre.web.controllers.FrontPageController;
import com.tiernebre.web.controllers.HealthController;

public final class RouterFactory {

  public Router create() {
    return new Router(new FrontPageController(), new HealthController());
  }
}
