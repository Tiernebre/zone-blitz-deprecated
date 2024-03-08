package com.tiernebre.authentication.registration;

public record CreateRegistrationRequest(
  String username,
  String password,
  String confirmPassword
) {}
