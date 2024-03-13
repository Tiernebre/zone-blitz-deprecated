package com.tiernebre.web.controllers;

import com.tiernebre.web.templates.Index;
import io.javalin.http.Context;
import io.jstach.jstachio.JStachio;

public final class FrontPageController {

  public void render(Context ctx) {
    var output = new StringBuilder();
    JStachio.render(new Index(), output);
    ctx.html(output.toString());
  }
}
