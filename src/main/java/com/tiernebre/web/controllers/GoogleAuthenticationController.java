package com.tiernebre.web.controllers;

import com.tiernebre.authentication.GoogleAuthenticationRequest;
import com.tiernebre.authentication.GoogleAuthenticationService;
import io.javalin.http.Context;

public final class GoogleAuthenticationController {

  private static final String CREDENTIAL_FIELD_NAME = "credential";
  private static final String CSRF_TOKEN_FIELD_NAME = "g_csrf_token";
  private final GoogleAuthenticationService authenticationService;

  public GoogleAuthenticationController(
    GoogleAuthenticationService authenticationService
  ) {
    this.authenticationService = authenticationService;
  }

  public void handleGoogleSignOn(Context context) {
    authenticationService.login(
      new GoogleAuthenticationRequest(
        context.formParam(CREDENTIAL_FIELD_NAME),
        context.formParam(CSRF_TOKEN_FIELD_NAME),
        context.cookie(CSRF_TOKEN_FIELD_NAME)
      )
    );
    context.redirect("/");
  }
}
