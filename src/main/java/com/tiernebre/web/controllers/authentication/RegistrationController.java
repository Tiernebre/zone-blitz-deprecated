package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.registration.CreateRegistrationRequest;
import com.tiernebre.authentication.registration.RegistrationService;
import com.tiernebre.web.constants.WebConstants;
import com.tiernebre.web.templates.RegistrationPage;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.jstach.jstachio.JStachio;

public final class RegistrationController {

  private final RegistrationService service;

  public RegistrationController(RegistrationService service) {
    this.service = service;
  }

  public void create(Context ctx) {
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

  public void page(Context ctx) {
    var output = new StringBuilder();
    JStachio.render(
      new RegistrationPage(
        WebConstants.Authentication.REGISTRATION_USERNAME_PARAM,
        WebConstants.Authentication.REGISTRATION_PASSWORD_PARAM,
        WebConstants.Authentication.REGISTRATION_CONFIRM_PASSWORD_PARAM
      ),
      output
    );
    ctx.html(output.toString());
  }
}
