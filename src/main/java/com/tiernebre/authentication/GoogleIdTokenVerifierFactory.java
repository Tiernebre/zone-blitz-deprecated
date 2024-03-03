package com.tiernebre.authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public final class GoogleIdTokenVerifierFactory {

  private final String clientId;

  public GoogleIdTokenVerifierFactory(String clientId) {
    this.clientId = clientId;
  }

  public GoogleIdTokenVerifier create()
    throws GeneralSecurityException, IOException {
    return new GoogleIdTokenVerifier.Builder(
      GoogleNetHttpTransport.newTrustedTransport(),
      GsonFactory.getDefaultInstance()
    )
      .setAudience(Collections.singletonList(clientId))
      .build();
  }
}
