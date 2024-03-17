package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.session.Session;
import io.javalin.http.Context;

public interface SessionRegister {
  /**
   * Registers a given session onto a given Javalin Context. Used for associating the
   * requested user on their future requests.
   *
   * @param ctx Javalin context
   * @param session The session to register.
   */
  public void register(Context ctx, Session session);
}
