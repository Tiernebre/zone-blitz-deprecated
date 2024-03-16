package com.tiernebre.web.controllers;

import com.tiernebre.authentication.session.Session;
import io.javalin.http.Context;
import io.vavr.control.Option;
import java.io.IOException;

public interface ControllerHelper {
  public Option<Session> session(Context ctx);

  public void renderHtml(Context ctx, Object model) throws IOException;
}
