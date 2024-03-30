package com.tiernebre.web.util;

import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionService;
import com.tiernebre.web.constants.WebConstants;
import com.tiernebre.web.controllers.authentication.AuthenticationWebConstants;
import io.javalin.http.Context;
import io.javalin.http.Cookie;
import io.vavr.control.Option;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
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
  public Option<Session> refresh(Context ctx) {
    return parse(ctx).map(session -> {
      var now = LocalDateTime.now(clock);
      var expiresAt = session.expiresAt();
      if (
        now.isBefore(expiresAt) &&
        now.isAfter(expiresAt.minus(WebConstants.SESSION_REFRESH_WINDOW))
      ) {
        var newSession = service.create(session.accountId());
        register(ctx, newSession);
        service.delete(session.id());
        return newSession;
      } else {
        return session;
      }
    });
  }

  @Override
  public void register(Context ctx, Session session) {
    Cookie sessionCookie = CookieUtil.secure(
      new Cookie(
        WebConstants.SESSION_COOKIE_TOKEN_NAME,
        session.id().toString()
      )
    );
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
    CookieUtil.delete(ctx, WebConstants.SESSION_COOKIE_TOKEN_NAME, true);
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
  public Option<Session> parse(Context ctx) {
    return Option.of(
      (Session) ctx.attribute(WebConstants.JAVALIN_SESSION_ATTRIBUTE)
    ).orElse(
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
        })
    );
  }

  private void deleteThirdPartyCookies(Context ctx) {
    List.of(
      AuthenticationWebConstants.GOOGLE_CSRF_TOKEN_FIELD_NAME,
      AuthenticationWebConstants.GOOGLE_STATE_FIELD_NAME
    ).forEach(name -> CookieUtil.delete(ctx, name));
  }
}
