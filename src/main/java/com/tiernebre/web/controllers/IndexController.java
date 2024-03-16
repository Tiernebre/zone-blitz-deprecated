package com.tiernebre.web.controllers;

import com.tiernebre.web.templates.Index;
import io.javalin.http.Context;
import java.io.IOException;

public final class IndexController {

  private final ControllerHelper helper;

  public IndexController(ControllerHelper helper) {
    this.helper = helper;
  }

  public void render(Context ctx) throws IOException {
    helper.template(ctx, new Index());
  }
}
