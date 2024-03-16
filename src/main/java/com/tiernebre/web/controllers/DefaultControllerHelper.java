package com.tiernebre.web.controllers;

import com.tiernebre.authentication.session.Session;
import com.tiernebre.web.constants.WebConstants;
import io.javalin.http.ContentType;
import io.javalin.http.Context;
import io.jstach.jstachio.JStachio;
import io.jstach.jstachio.Output;
import io.jstach.jstachio.context.ContextJStachio;
import io.jstach.jstachio.context.ContextNode;
import io.vavr.control.Option;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public final class DefaultControllerHelper implements ControllerHelper {

  @Override
  public void template(Context ctx, Object model) throws IOException {
    Map<String, Boolean> contextAttributes = new HashMap<>();
    session(ctx).peek(__ -> contextAttributes.put("loggedIn", true));
    ContextJStachio.of(JStachio.of()).execute(
      model,
      ContextNode.of(contextAttributes::get),
      Output.of(ctx.res().getOutputStream(), Charset.forName("UTF-8"))
    );
    ctx.contentType(ContentType.TEXT_HTML);
  }

  @Override
  public Option<Session> session(Context ctx) {
    return Option.of(ctx.attribute(WebConstants.JAVALIN_SESSION_ATTRIBUTE));
  }
}
