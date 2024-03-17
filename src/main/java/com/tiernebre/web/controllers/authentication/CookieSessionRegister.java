package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.session.Session;
import com.tiernebre.web.constants.WebConstants;
import io.javalin.http.Context;
import io.javalin.http.Cookie;
import io.javalin.http.HttpStatus;
import io.javalin.http.SameSite;

public class CookieSessionRegister implements SessionRegister {

  @Override
  public void register(Context ctx, Session session) {
    Cookie sessionCookie = new Cookie(
      WebConstants.SESSION_COOKIE_TOKEN_NAME,
      session.id().toString()
    );
    sessionCookie.setHttpOnly(true);
    sessionCookie.setSecure(true);
    sessionCookie.setPath("/");
    sessionCookie.setSameSite(SameSite.STRICT);
    ctx.cookie(sessionCookie);
    ctx.status(HttpStatus.CREATED);
    ctx.redirect("/");
  }
}
