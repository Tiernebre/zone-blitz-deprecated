package com.tiernebre.web.templates;

public record AuthenticationForm(
  String usernameFieldName,
  String passwordFieldName,
  int usernameMaxLength,
  int passwordMaxLength,
  int passwordMinLength
) {}
