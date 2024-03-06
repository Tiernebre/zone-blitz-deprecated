package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.google.GoogleAuthenticationRequest;
import com.tiernebre.authentication.google.GoogleAuthenticationStrategy;
import io.javalin.http.Context;
import io.javalin.http.Cookie;
import io.javalin.http.HttpStatus;
import io.javalin.http.SameSite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GoogleAuthenticationController
  implements AuthenticationController {

  private static final Logger LOG = LoggerFactory.getLogger(
    GoogleAuthenticationController.class
  );
  private static final String CREDENTIAL_FIELD_NAME = "credential";
  private static final String CSRF_TOKEN_FIELD_NAME = "g_csrf_token";
  private final GoogleAuthenticationStrategy authenticationService;

  public GoogleAuthenticationController(
    GoogleAuthenticationStrategy authenticationService
  ) {
    this.authenticationService = authenticationService;
  }

  @Override
  public void handle(Context context) {
    authenticationService
      .authenticate(
        new GoogleAuthenticationRequest(
          context.formParam(CREDENTIAL_FIELD_NAME),
          context.formParam(CSRF_TOKEN_FIELD_NAME),
          context.cookie(CSRF_TOKEN_FIELD_NAME)
        )
      )
      .peek(session -> {
        Cookie sessionCookie = new Cookie(
          WebAuthenticationConstants.SESSION_COOKIE_TOKEN_NAME,
          session.id().toString()
        );
        sessionCookie.setHttpOnly(true);
        sessionCookie.setSecure(true);
        sessionCookie.setPath("/");
        sessionCookie.setSameSite(SameSite.STRICT);
        context.cookie(sessionCookie);
        context.status(HttpStatus.CREATED);
        context.redirect("/");
        LOG.debug(
          String.format(
            "Created valid Google authentication session for accountId=%s",
            session.accountId()
          )
        );
      })
      .orElseRun(error -> {
        LOG.debug(
          "Could not create Google authentication session due to error=%s" +
          error
        );
        context.status(HttpStatus.BAD_REQUEST);
        context.redirect("/login");
      });
  }
}
