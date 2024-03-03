package com.tiernebre.authentication;

import com.tiernebre.authentication.google.GoogleAuthenticationService;

public record AuthenticationContext(
  AuthenticationConfiguration configuration,
  GoogleAuthenticationService googleAuthenticationService
) {}
