package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.registration.RegistrationService;
import com.tiernebre.authentication.registration.RegistrationValidator;
import io.javalin.http.Context;

public final class RegistrationController {

  private final RegistrationValidator validator;
  private final RegistrationService service;

  public RegistrationController(
    RegistrationValidator validator,
    RegistrationService service
  ) {
    this.validator = validator;
    this.service = service;
  }

  public void handle(Context ctx) {}
}
