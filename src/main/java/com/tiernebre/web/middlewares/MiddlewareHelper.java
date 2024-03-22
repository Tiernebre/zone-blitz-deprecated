package com.tiernebre.web.middlewares;

import com.tiernebre.authentication.session.Session;
import io.javalin.http.Context;
import io.vavr.control.Option;

public interface MiddlewareHelper {
  /**
   * Returns the loaded upstream Session fetched from the SessionMiddleware.
   *
   * @param ctx Javalin context
   * @return A filled out Option with a Session if the user is logged in, empty otherwise.
   */
  public Option<Session> session(Context ctx);
}
