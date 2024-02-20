package com.tiernebre.web.controllers;

import com.tiernebre.web.templates.FrontPage;
import io.javalin.http.Context;
import io.jstach.jstachio.JStachio;

public final class FrontPageController {

  public void render(Context ctx) {
    var hello = new FrontPage();
    var output = new StringBuilder();
    JStachio.render(hello, output);
    ctx.html(output.toString());
  }
}
