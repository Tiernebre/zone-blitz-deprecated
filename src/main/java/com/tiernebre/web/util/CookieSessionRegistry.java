package com.tiernebre.web.util;

import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionService;
import com.tiernebre.web.constants.WebConstants;
import com.tiernebre.web.controllers.authentication.AuthenticationWebConstants;
import io.javalin.http.Context;
import io.javalin.http.Cookie;
import io.javalin.http.SameSite;
import io.vavr.collection.List;
import io.vavr.control.Option;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CookieSessionRegistry implements SessionRegistry {

  private static final Logger LOG = LoggerFactory.getLogger(
    CookieSessionRegistry.class
  );

  private final SessionService service;
  private final Clock clock;

  public CookieSessionRegistry(SessionService service, Clock clock) {
    this.service = service;
    this.clock = clock;
  }

  @Override
  public void register(Context ctx, Session session) {
    Cookie sessionCookie = new Cookie(
      WebConstants.SESSION_COOKIE_TOKEN_NAME,
      session.id().toString()
    );
    secureCookie(sessionCookie);
    sessionCookie.setMaxAge(
      (int) Duration.between(
        LocalDateTime.now(clock),
        session.expiresAt()
      ).getSeconds()
    );
    ctx.cookie(sessionCookie);
    deleteThirdPartyCookies(ctx);
    LOG.debug(
      "Registered cookie based session for accountId={}",
      session.accountId()
    );
  }

  @Override
  public void delete(Context ctx, Session session) {
    deleteCookie(ctx, WebConstants.SESSION_COOKIE_TOKEN_NAME, true);
    deleteThirdPartyCookies(ctx);
    Option.of(session)
      .onEmpty(() -> {
        LOG.debug(
          "An attempt to delete an empty cookie based session occurred."
        );
      })
      .map(Session::id)
      .flatMap(service::delete)
      .peek(deletedSession -> {
        LOG.debug(
          "Deleted cookie based session tied to accountId={}.",
          deletedSession.accountId()
        );
      })
      .onEmpty(() -> {
        LOG.debug(
          "There was no cookie based session found to delete. No-oping the deletion request."
        );
      });
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
        LOG.debug(
          "Parsed given cookie session token for accountId={}",
          session.accountId()
        );
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

  private void deleteThirdPartyCookies(Context ctx) {
    List.of(
      AuthenticationWebConstants.GOOGLE_CSRF_TOKEN_FIELD_NAME,
      AuthenticationWebConstants.GOOGLE_STATE_FIELD_NAME
    ).forEach(name -> this.deleteCookie(ctx, name));
  }

  private void deleteCookie(Context ctx, String name) {
    deleteCookie(ctx, name, false);
  }

  private void deleteCookie(Context ctx, String name, boolean secure) {
    Cookie deletedCookie = new Cookie(name, "");
    deletedCookie.setMaxAge(0);
    if (secure) secureCookie(deletedCookie);
    ctx.cookie(deletedCookie);
  }
}
