package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.AuthenticationConstants;
import com.tiernebre.web.templates.AuthenticationForm;

final class Constants {

  static final String USERNAME_PARAM = "username";
  static final String PASSWORD_PARAM = "password";
  static final AuthenticationForm AUTHENTICATION_FORM = new AuthenticationForm(
    USERNAME_PARAM,
    PASSWORD_PARAM,
    AuthenticationConstants.USERNAME_MAXIMUM_LENGTH,
    AuthenticationConstants.PASSWORD_MAXIMUM_LENGTH,
    AuthenticationConstants.PASSWORD_MINIMUM_LENGTH
  );
  static final String CONFIRM_PASSWORD_PARAM = "confirmPassword";
  static final String GOOGLE_ACCOUNTS_URL = "https://accounts.google.com";
}
