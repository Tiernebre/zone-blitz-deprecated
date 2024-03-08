package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.registration.CreateRegistrationRequest;
import com.tiernebre.authentication.registration.RegistrationService;
import com.tiernebre.web.constants.WebConstants;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

public final class RegistrationController {

  private final RegistrationService service;

  public RegistrationController(RegistrationService service) {
    this.service = service;
  }

  public void handle(Context ctx) {
    service
      .create(
        new CreateRegistrationRequest(
          ctx.formParam(
            WebConstants.Authentication.REGISTRATION_USERNAME_PARAM
          ),
          ctx.formParam(
            WebConstants.Authentication.REGISTRATION_PASSWORD_PARAM
          ),
          ctx.formParam(
            WebConstants.Authentication.REGISTRATION_CONFIRM_PASSWORD_PARAM
          )
        )
      )
      .peek(registration -> {
        ctx.status(HttpStatus.CREATED);
      })
      .peekLeft(errors -> {
        ctx.status(HttpStatus.BAD_REQUEST);
      });
  }
}
