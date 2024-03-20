package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.session.Session;
import io.javalin.http.Context;

public interface SessionRegistry {
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

  /**
   * Parses a given session token provided by the client through the Javalin context. It will then associate
   * the this session within the Javalin context for downstream and upstream
   * middlewares to consume.
   *
   * @param ctx Javalin context to parse.
   */
  public void parse(Context ctx);
}
