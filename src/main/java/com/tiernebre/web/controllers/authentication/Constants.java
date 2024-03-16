package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.AuthenticationConstants;
import com.tiernebre.web.constants.WebConstants;
import com.tiernebre.web.templates.AuthenticationForm;

final class Constants {

  static final AuthenticationForm AUTHENTICATION_FORM = new AuthenticationForm(
    WebConstants.Authentication.USERNAME_PARAM,
    WebConstants.Authentication.PASSWORD_PARAM,
    AuthenticationConstants.USERNAME_MAXIMUM_LENGTH,
    AuthenticationConstants.PASSWORD_MAXIMUM_LENGTH,
    AuthenticationConstants.PASSWORD_MINIMUM_LENGTH
  );
}
