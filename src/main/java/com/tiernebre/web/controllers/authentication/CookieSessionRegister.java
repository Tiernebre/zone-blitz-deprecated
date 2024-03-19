package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.session.Session;
import com.tiernebre.web.constants.WebConstants;
import io.javalin.http.Context;
import io.javalin.http.Cookie;
import io.javalin.http.SameSite;

public class CookieSessionRegister implements SessionRegister {

  @Override
  public void register(Context ctx, Session session) {
    Cookie sessionCookie = new Cookie(
      WebConstants.SESSION_COOKIE_TOKEN_NAME,
      session.id().toString()
    );
    secureCookie(sessionCookie);
    ctx.cookie(sessionCookie);
    ctx.redirect("/");
  }

  @Override
  public void delete(Context ctx, Session session) {
    Cookie deletedSessionCookie = new Cookie(
      WebConstants.SESSION_COOKIE_TOKEN_NAME,
      ""
    );
    deletedSessionCookie.setMaxAge(0);
    ctx.cookie(deletedSessionCookie);
  }

  private void secureCookie(Cookie cookie) {
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/");
    cookie.setSameSite(SameSite.STRICT);
  }
}
