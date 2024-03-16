package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.registration.CreateRegistrationRequest;
import com.tiernebre.authentication.registration.RegistrationService;
import com.tiernebre.web.templates.Registration;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.jstach.jstachio.JStachio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RegistrationController {

  private final Logger LOG = LoggerFactory.getLogger(
    RegistrationController.class
  );

  private final RegistrationService service;

  public RegistrationController(RegistrationService service) {
    this.service = service;
  }

  public void submit(Context ctx) {
    service
      .create(
        new CreateRegistrationRequest(
          ctx.formParam(Constants.USERNAME_PARAMETER),
          ctx.formParam(Constants.PASSWORD_PARAMETER),
          ctx.formParam(Constants.CONFIRM_PASSWORD_PARAMETER)
        )
      )
      .peek(registration -> {
        ctx.status(HttpStatus.CREATED);
        ctx.redirect("/");
        LOG.debug("Successful registration, redirecting to home page");
      })
      .peekLeft(error -> {
        ctx.status(HttpStatus.BAD_REQUEST);
        LOG.debug("Failed registration, got errors %s", error);
        ctx.html(render(error.publicMessage()));
      });
  }

  public void page(Context ctx) {
    ctx.html(render(null));
  }

  private String render(String error) {
    var output = new StringBuilder();
    JStachio.render(
      new Registration(
        Constants.SHARED_AUTHENTICATION_FORM,
        Constants.CONFIRM_PASSWORD_PARAMETER,
        error
      ),
      output
    );
    return output.toString();
  }
}
