package com.tiernebre.web.controllers;

import com.tiernebre.authentication.session.Session;
import com.tiernebre.web.constants.WebConstants;
import io.javalin.http.Context;
import io.vavr.control.Option;

public class DefaultControllerHelper implements ControllerHelper {

  @Override
  public Option<Session> session(Context ctx) {
    return Option.of(ctx.attribute(WebConstants.JAVALIN_SESSION_ATTRIBUTE));
  }
}
