package com.tiernebre.authentication;

import com.tiernebre.authentication.google.GoogleAuthenticationStrategy;
import com.tiernebre.authentication.registration.RegistrationService;
import com.tiernebre.authentication.session.SessionService;

public record AuthenticationContext(
  AuthenticationConfiguration configuration,
  GoogleAuthenticationStrategy googleAuthenticationService,
  SessionService sessionService,
  RegistrationService registrationService
) {}
