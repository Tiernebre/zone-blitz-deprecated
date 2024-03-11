package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.AuthenticationConstants;
import com.tiernebre.authentication.registration.CreateRegistrationRequest;
import com.tiernebre.authentication.registration.RegistrationService;
import com.tiernebre.web.constants.WebConstants;
import com.tiernebre.web.templates.RegistrationPage;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.jstach.jstachio.JStachio;
import java.util.stream.Collectors;
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
        ctx.redirect("/");
        LOG.debug("Successful registration, redirecting to home page");
      })
      .peekLeft(errors -> {
        ctx.status(HttpStatus.BAD_REQUEST);
        ctx.redirect("/registration");
        LOG.debug(
          "Failed registration, got errors ",
          errors.collect(Collectors.joining("\n"))
        );
      });
  }

  public void page(Context ctx) {
    var output = new StringBuilder();
    JStachio.render(
      new RegistrationPage(
        WebConstants.Authentication.REGISTRATION_USERNAME_PARAM,
        WebConstants.Authentication.REGISTRATION_PASSWORD_PARAM,
        WebConstants.Authentication.REGISTRATION_CONFIRM_PASSWORD_PARAM,
        AuthenticationConstants.USERNAME_MAXIMUM_LENGTH
      ),
      output
    );
    ctx.html(output.toString());
  }
}
