package com.tiernebre.web.util;

import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionService;
import com.tiernebre.web.constants.WebConstants;
import io.javalin.http.Context;
import io.javalin.http.Cookie;
import io.javalin.http.SameSite;
import io.vavr.control.Option;
import java.util.UUID;

public final class CookieSessionRegistry implements SessionRegistry {

  private final SessionService service;

  public CookieSessionRegistry(SessionService service) {
    this.service = service;
  }

  @Override
  public void register(Context ctx, Session session) {
    Cookie sessionCookie = new Cookie(
      WebConstants.SESSION_COOKIE_TOKEN_NAME,
      session.id().toString()
    );
    secureCookie(sessionCookie);
    ctx.cookie(sessionCookie);
  }

  @Override
  public void delete(Context ctx, Session session) {
    Cookie deletedSessionCookie = new Cookie(
      WebConstants.SESSION_COOKIE_TOKEN_NAME,
      ""
    );
    deletedSessionCookie.setMaxAge(0);
    secureCookie(deletedSessionCookie);
    ctx.cookie(deletedSessionCookie);
  }

  @Override
  public void parse(Context ctx) {
    Option.of(ctx.cookie(WebConstants.SESSION_COOKIE_TOKEN_NAME))
      .toTry()
      .mapTry(token -> UUID.fromString(token))
      .toOption()
      .flatMap(service::get)
      .peek(session -> {
        ctx.attribute(WebConstants.JAVALIN_SESSION_ATTRIBUTE, session);
      });
  }

  @Override
  public void refresh(Context ctx, Session session) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'refresh'");
  }

  private void secureCookie(Cookie cookie) {
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/");
    cookie.setSameSite(SameSite.STRICT);
  }
}
