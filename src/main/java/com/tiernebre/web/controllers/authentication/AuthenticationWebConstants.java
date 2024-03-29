package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.AuthenticationConstants;
import com.tiernebre.web.constants.WebConstants;
import com.tiernebre.web.templates.AuthenticationForm;
import com.tiernebre.web.templates.GoogleSignOnButtonConfiguration;

public final class AuthenticationWebConstants {

  public static final String USERNAME_PARAMETER = "username";
  public static final String PASSWORD_PARAMETER = "password";
  public static final String CONFIRM_PASSWORD_PARAMETER = "confirmPassword";
  public static final AuthenticationForm SHARED_AUTHENTICATION_FORM =
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
  public static final GoogleSignOnButtonConfiguration GOOGLE_SIGN_ON_BUTTON_CONFIGURATION =
    new GoogleSignOnButtonConfiguration(
      GOOGLE_CLIENT_ID,
      String.format("%s/login", WebConstants.URL),
      String.format(
        "%s/gsi/client",
        AuthenticationWebConstants.GOOGLE_ACCOUNTS_URL
      )
    );
}
