package com.tiernebre.web.controllers;

import com.tiernebre.authentication.session.Session;
import com.tiernebre.web.templates.Index;
import io.javalin.http.Context;
import io.jstach.jstachio.JStachio;

public final class IndexController {

  private final ControllerHelper helper;

  public IndexController(ControllerHelper helper) {
    this.helper = helper;
  }

  public void render(Context ctx) {
    var output = new StringBuilder();
    JStachio.render(
      new Index(
        helper
          .session(ctx)
          .map(Session::accountId)
          .map(Object::toString)
          .getOrElse("null")
      ),
      output
    );
    ctx.html(output.toString());
  }
}
