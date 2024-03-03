package com.tiernebre.web.controllers;

import com.tiernebre.authentication.google.GoogleAuthenticationRequest;
import com.tiernebre.authentication.google.GoogleAuthenticationStrategy;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GoogleAuthenticationController {

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

  public void handleGoogleSignOn(Context context) {
    authenticationService.authenticate(
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
  }
}
