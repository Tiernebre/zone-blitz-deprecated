package com.tiernebre.authentication;

import com.tiernebre.authentication.google.GoogleAuthenticationStrategy;

public record AuthenticationContext(
  AuthenticationConfiguration configuration,
  GoogleAuthenticationStrategy googleAuthenticationService
) {}
