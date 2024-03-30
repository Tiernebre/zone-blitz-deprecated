package com.tiernebre.web.util;

import io.javalin.http.Context;
import io.javalin.http.Cookie;
import io.javalin.http.SameSite;

public final class CookieUtil {

  public static void delete(Context ctx, String name) {
    ctx.cookie(delete(name));
  }

  public static Cookie delete(String name) {
    return delete(name, false);
  }

  public static void delete(Context ctx, String name, boolean secure) {
    ctx.cookie(delete(name, secure));
  }

  public static Cookie delete(String name, boolean secure) {
    Cookie deletedCookie = new Cookie(name, "");
    deletedCookie.setMaxAge(0);
    if (secure) CookieUtil.secure(deletedCookie);
    return deletedCookie;
  }

  public static Cookie secure(Cookie cookie) {
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/");
    cookie.setSameSite(SameSite.STRICT);
    return cookie;
  }
}
