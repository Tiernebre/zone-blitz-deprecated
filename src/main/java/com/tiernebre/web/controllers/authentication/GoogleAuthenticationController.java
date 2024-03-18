package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.google.GoogleAuthenticationRequest;
import com.tiernebre.authentication.google.GoogleAuthenticationStrategy;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GoogleAuthenticationController
  implements AuthenticationController {

  private static final Logger LOG = LoggerFactory.getLogger(
    GoogleAuthenticationController.class
  );

  private final GoogleAuthenticationStrategy authenticationStrategy;
  private final SessionRegister sessionRegister;

  public GoogleAuthenticationController(
    GoogleAuthenticationStrategy authenticationService,
    SessionRegister sessionRegister
  ) {
    this.authenticationStrategy = authenticationService;
    this.sessionRegister = sessionRegister;
  }

  @Override
  public void handle(Context ctx) {
    authenticationStrategy
      .authenticate(
        new GoogleAuthenticationRequest(
          ctx.formParam(Constants.GOOGLE_CREDENTIAL_FIELD_NAME),
          ctx.formParam(Constants.GOOGLE_CSRF_TOKEN_FIELD_NAME),
          ctx.cookie(Constants.GOOGLE_CSRF_TOKEN_FIELD_NAME)
        )
      )
      .peek(session -> {
        sessionRegister.register(ctx, session);
        ctx.cookie(Constants.GOOGLE_CSRF_TOKEN_FIELD_NAME, "", 0);
        ctx.cookie(Constants.GOOGLE_STATE_FIELD_NAME, "", 0);
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
        ctx.status(HttpStatus.BAD_REQUEST);
        ctx.redirect("/login");
      });
  }
}
