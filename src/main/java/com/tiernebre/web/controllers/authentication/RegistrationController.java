package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.registration.CreateRegistrationRequest;
import com.tiernebre.authentication.registration.RegistrationAuthenticationRequest;
import com.tiernebre.authentication.registration.RegistrationAuthenticationStrategy;
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
  private final RegistrationAuthenticationStrategy authenticationStrategy;
  private final SessionRegister sessionRegister;

  public RegistrationController(
    RegistrationService service,
    RegistrationAuthenticationStrategy authenticationStrategy,
    SessionRegister sessionRegister
  ) {
    this.service = service;
    this.authenticationStrategy = authenticationStrategy;
    this.sessionRegister = sessionRegister;
  }

  public void submit(Context ctx) {
    var password = ctx.formParam(Constants.PASSWORD_PARAMETER);
    service
      .create(
        new CreateRegistrationRequest(
          ctx.formParam(Constants.USERNAME_PARAMETER),
          password,
          ctx.formParam(Constants.CONFIRM_PASSWORD_PARAMETER)
        )
      )
      .map(
        registration ->
          new RegistrationAuthenticationRequest(
            registration.username(),
            password
          )
      )
      .flatMap(authenticationStrategy::authenticate)
      .peek(session -> {
        sessionRegister.register(ctx, session);
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
