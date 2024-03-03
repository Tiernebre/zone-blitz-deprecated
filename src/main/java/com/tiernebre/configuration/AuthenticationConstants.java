package com.tiernebre.configuration;

public final class AuthenticationConstants {

  public static final AuthenticationConfiguration CONFIGURATION =
    new AuthenticationConfiguration(
      System.getenv("ZONE_BLITZ_OAUTH_GOOGLE_CLIENT_ID")
    );
}
