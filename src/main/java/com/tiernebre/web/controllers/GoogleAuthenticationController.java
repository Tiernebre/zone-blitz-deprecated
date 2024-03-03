package com.tiernebre.web.controllers;

import com.tiernebre.authentication.google.GoogleAuthenticationRequest;
import com.tiernebre.authentication.google.GoogleAuthenticationService;
import com.tiernebre.authentication.google.InvalidGoogleSignOnAttemptException;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GoogleAuthenticationController {

  private static final Logger LOG = LoggerFactory.getLogger(
    GoogleAuthenticationController.class
  );
  private static final String CREDENTIAL_FIELD_NAME = "credential";
  private static final String CSRF_TOKEN_FIELD_NAME = "g_csrf_token";
  private final GoogleAuthenticationService authenticationService;

  public GoogleAuthenticationController(
    GoogleAuthenticationService authenticationService
  ) {
    this.authenticationService = authenticationService;
  }

  public void handleGoogleSignOn(Context context) {
    try {
      authenticationService.login(
        new GoogleAuthenticationRequest(
          context.formParam(CREDENTIAL_FIELD_NAME),
          context.formParam(CSRF_TOKEN_FIELD_NAME),
          context.cookie(CSRF_TOKEN_FIELD_NAME)
        )
      );
      LOG.debug(
        String.format("Received and processed a valid google sign on attempt.")
      );
      context.redirect("/");
    } catch (InvalidGoogleSignOnAttemptException e) {
      LOG.info(
        String.format("Received an invalid google sign on attempt error: %s", e)
      );
      context.redirect("/");
    }
  }
}
