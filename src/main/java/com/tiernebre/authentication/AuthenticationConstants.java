package com.tiernebre.authentication;

public class AuthenticationConstants {

  public static final AuthenticationConfiguration CONFIGURATION =
    new AuthenticationConfiguration(
      System.getenv("ZONE_BLITZ_OAUTH_GOOGLE_CLIENT_ID")
    );

  public static final int USERNAME_MAXIMUM_LENGTH = 64;
  public static final int PASSWORD_MAXIMUM_LENGTH = 64;
  public static final int PASSWORD_MINIMUM_LENGTH = 8;
}
