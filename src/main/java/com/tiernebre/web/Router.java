package com.tiernebre.web;

import com.tiernebre.web.templates.FrontPage;
import io.javalin.Javalin;
import io.jstach.jstachio.JStachio;

public class Router {

  public Javalin register(Javalin app) {
    return app.get(
      "/",
      ctx -> {
        var hello = new FrontPage();
        var output = new StringBuilder();
        JStachio.render(hello, output);
        ctx.html(output.toString());
      }
    );
  }
}
