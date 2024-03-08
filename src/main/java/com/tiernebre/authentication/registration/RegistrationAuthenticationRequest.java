package com.tiernebre.authentication.registration;

public record RegistrationAuthenticationRequest(
  String username,
  String password
) {}
