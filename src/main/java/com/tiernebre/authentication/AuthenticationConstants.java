package com.tiernebre.authentication;

public class AuthenticationConstants {

  public static final AuthenticationConfiguration CONFIGURATION =
    new AuthenticationConfiguration(
      System.getenv("ZONE_BLITZ_OAUTH_GOOGLE_CLIENT_ID")
    );
}
