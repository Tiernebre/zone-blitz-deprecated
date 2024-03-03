package com.tiernebre.web.controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.tiernebre.configuration.AuthenticationConstants;
import io.javalin.http.Context;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public final class AuthenticationController {

  public void handleGoogleSignOn(Context context) {
    try {
      var verifier = new GoogleIdTokenVerifier.Builder(
        GoogleNetHttpTransport.newTrustedTransport(),
        GsonFactory.getDefaultInstance()
      )
        .setAudience(
          Collections.singletonList(
            AuthenticationConstants.CONFIGURATION.oAuthGoogleClientId()
          )
        )
        .build();
      var token = verifier.verify(context.formParam("credential"));
      if (token != null) {
        var payload = token.getPayload();
        var userId = payload.getSubject();
      }
    } catch (GeneralSecurityException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    context.redirect("/");
  }
}
