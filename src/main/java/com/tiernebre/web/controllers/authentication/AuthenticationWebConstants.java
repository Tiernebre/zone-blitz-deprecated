package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.AuthenticationConstants;
import com.tiernebre.web.templates.AuthenticationForm;

public final class AuthenticationWebConstants {

  static final String USERNAME_PARAMETER = "username";
  static final String PASSWORD_PARAMETER = "password";
  static final String CONFIRM_PASSWORD_PARAMETER = "confirmPassword";
  static final AuthenticationForm SHARED_AUTHENTICATION_FORM =
    new AuthenticationForm(
      USERNAME_PARAMETER,
      PASSWORD_PARAMETER,
      AuthenticationConstants.USERNAME_MAXIMUM_LENGTH,
      AuthenticationConstants.PASSWORD_MAXIMUM_LENGTH,
      AuthenticationConstants.PASSWORD_MINIMUM_LENGTH
    );
  static final String GOOGLE_ACCOUNTS_URL = "https://accounts.google.com";

  static final String GOOGLE_CLIENT_ID = System.getenv(
    "ZONE_BLITZ_OAUTH_GOOGLE_CLIENT_ID"
  );
  static final String GOOGLE_CREDENTIAL_FIELD_NAME = "credential";
  public static final String GOOGLE_CSRF_TOKEN_FIELD_NAME = "g_csrf_token";
  public static final String GOOGLE_STATE_FIELD_NAME = "g_state";
}
