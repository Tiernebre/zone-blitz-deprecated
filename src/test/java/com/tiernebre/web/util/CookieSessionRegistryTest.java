package com.tiernebre.web.util;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
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
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.Test;

public final class CookieSessionRegistryTest {

  SessionService service = mock(SessionService.class);
  SessionRegistry registry = new CookieSessionRegistry(service);

  @Test
  public void register() {
    var ctx = mock(Context.class);
    var session = new Session(
      UUID.randomUUID(),
      1L,
      LocalDateTime.now(),
      false
    );
    var expectedCookie = new Cookie(
      WebConstants.SESSION_COOKIE_TOKEN_NAME,
      session.id().toString()
    );
    expectedCookie.setHttpOnly(true);
    expectedCookie.setSecure(true);
    expectedCookie.setPath("/");
    expectedCookie.setSameSite(SameSite.STRICT);
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
  }

  @Test
  public void parse() {
    TestCaseRunner.run(CookieSessionRegistryTest.class, List.of(
        new TestCase<Context, Void>(
          "null token",
          mock(Context.class),
          __ -> null,
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
        new TestCase<Context, Void>(
          "empty token",
          mock(Context.class),
          __ -> null,
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
        new TestCase<Context, Void>(
          "non UUID token",
          mock(Context.class),
          __ -> null,
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
        new TestCase<Context, Void>(
          "valid token",
          mock(Context.class),
          __ -> null,
          (ctx, __) -> {
            var token = UUID.randomUUID();
            when(ctx.cookie(WebConstants.SESSION_COOKIE_TOKEN_NAME)).thenReturn(
              token.toString()
            );
            when(service.get(token)).thenReturn(
              Option.of(new Session(token, 0, null, false))
            );
          },
          (ctx, __) -> {
            verify(ctx, times(1)).attribute(
              eq(WebConstants.JAVALIN_SESSION_ATTRIBUTE),
              any(Session.class)
            );
          }
        )
      ), context -> {
        registry.parse(context);
        return null;
      });
  }
}
