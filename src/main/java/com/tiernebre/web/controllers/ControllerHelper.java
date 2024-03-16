package com.tiernebre.web.controllers;

import com.tiernebre.authentication.session.Session;
import io.javalin.http.Context;
import io.vavr.control.Option;

public interface ControllerHelper {
  public Option<Session> session(Context ctx);
}
