package com.tiernebre.web.controllers;

import com.tiernebre.web.templates.Index;
import com.tiernebre.web.util.WebHelper;
import io.javalin.http.Context;
import java.io.IOException;

public final class IndexController {

  private final WebHelper helper;

  public IndexController(WebHelper helper) {
    this.helper = helper;
  }

  public void render(Context ctx) throws IOException {
    helper.template(ctx, new Index());
  }
}
