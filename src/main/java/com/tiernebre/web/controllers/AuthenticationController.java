package com.tiernebre.web.controllers;

import io.javalin.http.Context;

public class AuthenticationController {

  public void handleGoogleSignOn(Context context) {
    context.redirect("/");
  }
}
