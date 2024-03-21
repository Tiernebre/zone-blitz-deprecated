package com.tiernebre.web.util;

import com.tiernebre.authentication.session.Session;
import io.javalin.http.Context;
import io.vavr.control.Option;
import java.io.IOException;

public interface WebHelper {
  /**
   * Returns the loaded upstream Session fetched from the SessionMiddleware.
   *
   * @param ctx Javalin context
   * @return A filled out Option with a Session if the user is logged in, empty otherwise.
   */
  public Option<Session> session(Context ctx);

  /**
   * For a given context, renders a given JStachio template model into HTML.
   *
   * @param ctx Javalin context.
   * @param model The JStachio template model.
   * @throws IOException If writing the HTML out into the ersponse ran into an error.
   */
  public void template(Context ctx, Object model) throws IOException;
}
