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

  /**
   * Deletes a given session. Used for scenarios where the user wants to
   * log out of their session.
   *
   * @param ctx Javalin context
   * @param session The session to delete.
   */
  public void delete(Context ctx, Session session);
}
