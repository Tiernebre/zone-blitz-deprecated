package com.tiernebre.web.util;

import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionService;
import com.tiernebre.web.constants.WebConstants;
import io.javalin.http.Context;
import io.javalin.http.Cookie;
import io.javalin.http.SameSite;
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
    LOG.debug(
      "Registered cookie based session for accountId={}",
      session.accountId()
    );
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
}
