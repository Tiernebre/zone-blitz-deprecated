package com.tiernebre.web.templates.pages;

import com.tiernebre.authentication.AuthenticationConstants;
import com.tiernebre.web.constants.WebConstants;
import io.jstach.jstache.JStache;

@JStache(path = "registration")
public record RegistrationPage(
  String usernameFieldName,
  String passwordFieldName,
  String confirmPasswordFieldName,
  int usernameMaxLength,
  int passwordMaxLength,
  int passwordMinLength,
  String error
) {
  public RegistrationPage(String error) {
    this(
      WebConstants.Authentication.REGISTRATION_USERNAME_PARAM,
      WebConstants.Authentication.REGISTRATION_PASSWORD_PARAM,
      WebConstants.Authentication.REGISTRATION_CONFIRM_PASSWORD_PARAM,
      AuthenticationConstants.USERNAME_MAXIMUM_LENGTH,
      AuthenticationConstants.PASSWORD_MAXIMUM_LENGTH,
      AuthenticationConstants.PASSWORD_MINIMUM_LENGTH,
      error
    );
  }
}
