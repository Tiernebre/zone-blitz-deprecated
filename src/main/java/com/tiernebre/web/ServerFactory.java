package com.tiernebre.web;

import com.tiernebre.authentication.AuthenticationConstants;
import com.tiernebre.authentication.AuthenticationContextFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;

public final class ServerFactory {

  public Server create() throws GeneralSecurityException, IOException {
    var authenticationContextFactory = new AuthenticationContextFactory(
      AuthenticationConstants.CONFIGURATION
    );
    return new Server(
      new RouterFactory(authenticationContextFactory.create()).create()
    );
  }
}
