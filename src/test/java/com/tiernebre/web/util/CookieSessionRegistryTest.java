package com.tiernebre.web.util;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionService;
import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import com.tiernebre.web.constants.WebConstants;
import io.javalin.http.Context;
import io.javalin.http.Cookie;
import io.javalin.http.SameSite;
import io.vavr.collection.List;
import io.vavr.control.Option;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import org.junit.Test;

public final class CookieSessionRegistryTest {

  SessionService service = mock(SessionService.class);
  Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
  SessionRegistry registry = new CookieSessionRegistry(service, clock);

  @Test
  public void register() {
    var ctx = mock(Context.class);
    var now = LocalDateTime.now(clock);
    var session = new Session(UUID.randomUUID(), 1L, now.plusHours(1), false);
    var expectedCookie = new Cookie(
      WebConstants.SESSION_COOKIE_TOKEN_NAME,
      session.id().toString()
    );
    expectedCookie.setHttpOnly(true);
    expectedCookie.setSecure(true);
    expectedCookie.setPath("/");
    expectedCookie.setSameSite(SameSite.STRICT);
    expectedCookie.setMaxAge(
      (int) Duration.between(now, session.expiresAt()).getSeconds()
    );
    registry.register(ctx, session);
    verify(ctx).cookie(expectedCookie);
  }

  @Test
  public void delete() {
    var ctx = mock(Context.class);
    var session = new Session(
      UUID.randomUUID(),
      1L,
      LocalDateTime.now(),
      false
    );
    var expectedCookie = new Cookie(WebConstants.SESSION_COOKIE_TOKEN_NAME, "");
    expectedCookie.setHttpOnly(true);
    expectedCookie.setSecure(true);
    expectedCookie.setPath("/");
    expectedCookie.setSameSite(SameSite.STRICT);
    expectedCookie.setMaxAge(0);
    when(service.delete(session.id())).thenReturn(Option.of(session));
    registry.delete(ctx, session);
    verify(ctx).cookie(expectedCookie);
    verify(service, times(1)).delete(session.id());
  }

  @Test
  public void deleteEmpty() {
    var ctx = mock(Context.class);
    var expectedCookie = new Cookie(WebConstants.SESSION_COOKIE_TOKEN_NAME, "");
    expectedCookie.setHttpOnly(true);
    expectedCookie.setSecure(true);
    expectedCookie.setPath("/");
    expectedCookie.setSameSite(SameSite.STRICT);
    expectedCookie.setMaxAge(0);
    registry.delete(ctx, null);
    verify(ctx).cookie(expectedCookie);
    verify(service, times(0)).delete(any());
  }

  @Test
  public void parse() {
    TestCaseRunner.run(
      CookieSessionRegistryTest.class,
      List.of(
        new TestCase<Context, Option<Session>>(
          "already set session",
          mock(Context.class),
          __ -> Option.of(new Session(UUID.randomUUID(), 0, null, false)),
          (ctx, session) -> {
            when(
              ctx.attribute(WebConstants.JAVALIN_SESSION_ATTRIBUTE)
            ).thenReturn(session.get());
          },
          (ctx, __) -> {
            verify(ctx, times(0)).attribute(
              eq(WebConstants.JAVALIN_SESSION_ATTRIBUTE),
              any()
            );
          }
        ),
        new TestCase<Context, Option<Session>>(
          "null token",
          mock(Context.class),
          __ -> Option.none(),
          (ctx, __) -> {
            when(ctx.cookie(WebConstants.SESSION_COOKIE_TOKEN_NAME)).thenReturn(
              null
            );
          },
          (ctx, __) -> {
            verify(ctx, times(0)).attribute(
              eq(WebConstants.JAVALIN_SESSION_ATTRIBUTE),
              any()
            );
          }
        ),
        new TestCase<Context, Option<Session>>(
          "empty token",
          mock(Context.class),
          __ -> Option.none(),
          (ctx, __) -> {
            when(ctx.cookie(WebConstants.SESSION_COOKIE_TOKEN_NAME)).thenReturn(
              ""
            );
          },
          (ctx, __) -> {
            verify(ctx, times(0)).attribute(
              eq(WebConstants.JAVALIN_SESSION_ATTRIBUTE),
              any()
            );
          }
        ),
        new TestCase<Context, Option<Session>>(
          "non UUID token",
          mock(Context.class),
          __ -> Option.none(),
          (ctx, __) -> {
            when(ctx.cookie(WebConstants.SESSION_COOKIE_TOKEN_NAME)).thenReturn(
              "a"
            );
          },
          (ctx, __) -> {
            verify(ctx, times(0)).attribute(
              eq(WebConstants.JAVALIN_SESSION_ATTRIBUTE),
              any()
            );
          }
        ),
        new TestCase<Context, Option<Session>>(
          "valid token",
          mock(Context.class),
          __ -> Option.of(new Session(UUID.randomUUID(), 0, null, false)),
          (ctx, session) -> {
            var token = UUID.randomUUID();
            when(ctx.cookie(WebConstants.SESSION_COOKIE_TOKEN_NAME)).thenReturn(
              token.toString()
            );
            when(service.get(token)).thenReturn(session);
          },
          (ctx, __) -> {
            verify(ctx, times(1)).attribute(
              eq(WebConstants.JAVALIN_SESSION_ATTRIBUTE),
              any(Session.class)
            );
          }
        )
      ),
      context -> registry.parse(context)
    );
  }

  @Test
  public void refresh() {
    TestCaseRunner.run(
      CookieSessionRegistryTest.class,
      List.of(
        new TestCase<Context, Option<Session>>(
          "no previous session and no token",
          mock(Context.class),
          __ -> Option.none(),
          (ctx, session) -> {
            when(
              ctx.attribute(WebConstants.JAVALIN_SESSION_ATTRIBUTE)
            ).thenReturn(null);
            when(ctx.cookie(WebConstants.SESSION_COOKIE_TOKEN_NAME)).thenReturn(
              null
            );
          },
          (ctx, __) -> {
            verify(ctx, times(0)).cookie(
              eq(WebConstants.SESSION_COOKIE_TOKEN_NAME),
              any()
            );
            verify(service, times(0)).delete(any());
            reset(ctx);
            reset(service);
          }
        ),
        new TestCase<Context, Option<Session>>(
          "refreshes a given token",
          mock(Context.class),
          __ ->
            Option.of(
              new Session(
                UUID.randomUUID(),
                0,
                LocalDateTime.now().plusMinutes(10),
                false
              )
            ),
          (ctx, newSession) -> {
            var previousSession = new Session(
              UUID.randomUUID(),
              newSession.get().accountId(),
              LocalDateTime.now().plusMinutes(1),
              false
            );
            when(ctx.cookie(WebConstants.SESSION_COOKIE_TOKEN_NAME)).thenReturn(
              previousSession.id().toString()
            );
            when(service.get(previousSession.id())).thenReturn(
              Option.of(previousSession)
            );
            when(service.create(eq(previousSession.accountId()))).thenReturn(
              newSession.get()
            );
          },
          (ctx, __) -> {
            verify(service, times(1)).delete(any());
            reset(ctx);
            reset(service);
          }
        ),
        new TestCase<Context, Option<Session>>(
          "refreshes a given session",
          mock(Context.class),
          __ ->
            Option.of(
              new Session(
                UUID.randomUUID(),
                0,
                LocalDateTime.now().plusMinutes(10),
                false
              )
            ),
          (ctx, newSession) -> {
            var previousSession = new Session(
              UUID.randomUUID(),
              newSession.get().accountId(),
              LocalDateTime.now().plusMinutes(1),
              false
            );
            when(
              ctx.attribute(WebConstants.JAVALIN_SESSION_ATTRIBUTE)
            ).thenReturn(previousSession);
            when(service.create(eq(previousSession.accountId()))).thenReturn(
              newSession.get()
            );
          },
          (ctx, __) -> {
            verify(service, times(1)).delete(any());
            reset(ctx);
            reset(service);
          }
        ),
        new TestCase<Context, Option<Session>>(
          "does not refresh if before the window",
          mock(Context.class),
          __ ->
            Option.of(
              new Session(
                UUID.randomUUID(),
                0,
                LocalDateTime.now(clock).plusMinutes(10).plusSeconds(1),
                false
              )
            ),
          (ctx, session) -> {
            when(
              ctx.attribute(WebConstants.JAVALIN_SESSION_ATTRIBUTE)
            ).thenReturn(session.get());
          },
          (ctx, __) -> {
            verify(service, times(0)).delete(any());
            reset(ctx);
            reset(service);
          }
        ),
        new TestCase<Context, Option<Session>>(
          "does not refresh if after the window",
          mock(Context.class),
          __ ->
            Option.of(
              new Session(
                UUID.randomUUID(),
                0,
                LocalDateTime.now(clock).minusSeconds(1),
                false
              )
            ),
          (ctx, session) -> {
            when(
              ctx.attribute(WebConstants.JAVALIN_SESSION_ATTRIBUTE)
            ).thenReturn(session.get());
          },
          (ctx, __) -> {
            verify(service, times(0)).delete(any());
            reset(ctx);
            reset(service);
          }
        ),
        new TestCase<Context, Option<Session>>(
          "refreshes if in the window",
          mock(Context.class),
          __ ->
            Option.of(
              new Session(
                UUID.randomUUID(),
                0,
                LocalDateTime.now(clock).plusMinutes(60),
                false
              )
            ),
          (ctx, session) -> {
            var previousSession = new Session(
              UUID.randomUUID(),
              session.get().accountId(),
              LocalDateTime.now().plusMinutes(9).plusSeconds(59),
              false
            );
            when(
              ctx.attribute(WebConstants.JAVALIN_SESSION_ATTRIBUTE)
            ).thenReturn(previousSession);
            when(service.create(eq(previousSession.accountId()))).thenReturn(
              session.get()
            );
          },
          (ctx, __) -> {
            verify(service, times(1)).delete(any());
            reset(ctx);
            reset(service);
          }
        )
      ),
      context -> registry.refresh(context)
    );
  }
}
