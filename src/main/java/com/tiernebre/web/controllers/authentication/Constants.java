package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.AuthenticationConstants;
import com.tiernebre.web.templates.AuthenticationForm;

final class Constants {

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
}
