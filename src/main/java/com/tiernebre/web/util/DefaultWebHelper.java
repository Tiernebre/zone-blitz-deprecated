package com.tiernebre.web.util;

import com.tiernebre.authentication.session.Session;
import com.tiernebre.util.pagination.PageRequest;
import com.tiernebre.util.pagination.WebPaginationHelper;
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

public final class DefaultWebHelper implements WebHelper {

  private final SessionRegistry sessionRegistry;
  private final WebPaginationHelper paginationHelper;

  public DefaultWebHelper(
    SessionRegistry sessionRegistry,
    WebPaginationHelper paginationHelper
  ) {
    this.sessionRegistry = sessionRegistry;
    this.paginationHelper = paginationHelper;
  }

  @Override
  public void template(Context ctx, Object model) {
    Map<String, Boolean> contextAttributes = new HashMap<>();
    session(ctx).peek(__ -> contextAttributes.put("loggedIn", true));
    try {
      ContextJStachio.of(JStachio.of()).execute(
        model,
        ContextNode.of(contextAttributes::get),
        Output.of(ctx.res().getOutputStream(), Charset.forName("UTF-8"))
      );
    } catch (IOException e) {
      throw new RuntimeException(
        "Ran into an error when rendering a template."
      );
    }
    ctx.contentType(ContentType.TEXT_HTML);
  }

  @Override
  public Session authenticatedSession(Context ctx) {
    return session(ctx).getOrNull();
  }

  @Override
  public Option<Session> session(Context ctx) {
    return sessionRegistry.parse(ctx);
  }

  @Override
  public PageRequest pageRequest(Context ctx) {
    return paginationHelper.pageRequest(ctx);
  }
}
