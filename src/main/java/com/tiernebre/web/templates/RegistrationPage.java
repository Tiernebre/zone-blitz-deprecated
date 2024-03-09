package com.tiernebre.web.templates;

import io.jstach.jstache.JStache;

@JStache(path = "registration_page")
public record RegistrationPage(
  String usernameFieldName,
  String passwordFieldName,
  String confirmPasswordFieldName
) {}
