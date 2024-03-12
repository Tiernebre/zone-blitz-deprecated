package com.tiernebre.web.templates;

import com.tiernebre.authentication.AuthenticationConstants;
import com.tiernebre.web.constants.WebConstants;
import io.jstach.jstache.JStache;

@JStache(path = "registration_page")
public record RegistrationPage(
  String usernameFieldName,
  String passwordFieldName,
  String confirmPasswordFieldName,
  int usernameMaxLength,
  int passwordMaxLength,
  int passwordMinLength
) {
  public RegistrationPage() {
    this(
      WebConstants.Authentication.REGISTRATION_USERNAME_PARAM,
      WebConstants.Authentication.REGISTRATION_PASSWORD_PARAM,
      WebConstants.Authentication.REGISTRATION_CONFIRM_PASSWORD_PARAM,
      AuthenticationConstants.USERNAME_MAXIMUM_LENGTH,
      AuthenticationConstants.PASSWORD_MAXIMUM_LENGTH,
      AuthenticationConstants.PASSWORD_MINIMUM_LENGTH
    );
  }
}
