package com.tiernebre.web.templates;

import com.tiernebre.web.controllers.authentication.AuthenticationWebConstants;
import com.tiernebre.web.templates.interfaces.UsesForm;
import io.jstach.jstache.JStache;

@JStache(path = "registration")
public record Registration(String error) implements UsesForm {
  GoogleSignOnButtonConfiguration google() {
    return AuthenticationWebConstants.GOOGLE_SIGN_ON_BUTTON_CONFIGURATION;
  }

  AuthenticationForm form() {
    return AuthenticationWebConstants.SHARED_AUTHENTICATION_FORM;
  }

  String passwordAutocomplete() {
    return "new-password";
  }

  String confirmPasswordFieldName() {
    return AuthenticationWebConstants.CONFIRM_PASSWORD_PARAMETER;
  }
}
