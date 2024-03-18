package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.google.GoogleAuthenticationRequest;
import com.tiernebre.authentication.google.GoogleAuthenticationStrategy;
import com.tiernebre.authentication.registration.RegistrationAuthenticationRequest;
import com.tiernebre.authentication.registration.RegistrationAuthenticationStrategy;
import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.web.constants.WebConstants;
import com.tiernebre.web.controllers.ControllerHelper;
import com.tiernebre.web.templates.Login;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoginController {

  private final Logger LOG = LoggerFactory.getLogger(LoginController.class);

  private final ControllerHelper helper;
  private final GoogleAuthenticationStrategy googleAuthenticationStrategy;
  private final RegistrationAuthenticationStrategy registrationAuthenticationStrategy;
  private final SessionRegister sessionRegister;

  public LoginController(
    ControllerHelper helper,
    GoogleAuthenticationStrategy googleAuthenticationStrategy,
    RegistrationAuthenticationStrategy registrationAuthenticationStrategy,
    SessionRegister sessionRegister
  ) {
    this.helper = helper;
    this.googleAuthenticationStrategy = googleAuthenticationStrategy;
    this.registrationAuthenticationStrategy =
      registrationAuthenticationStrategy;
    this.sessionRegister = sessionRegister;
  }

  public void handle(Context ctx) {
    (StringUtils.isBlank(Constants.GOOGLE_CSRF_TOKEN_FIELD_NAME)
        ? registrationAuthenticationStrategy.authenticate(
          new RegistrationAuthenticationRequest(
            ctx.formParam(Constants.USERNAME_PARAMETER),
            ctx.formParam(Constants.PASSWORD_PARAMETER)
          )
        )
        : googleAuthenticationStrategy.authenticate(
          new GoogleAuthenticationRequest(
            ctx.formParam(Constants.GOOGLE_CREDENTIAL_FIELD_NAME),
            ctx.formParam(Constants.GOOGLE_CSRF_TOKEN_FIELD_NAME),
            ctx.cookie(Constants.GOOGLE_CSRF_TOKEN_FIELD_NAME)
          )
        )).peek(session -> {
        sessionRegister.register(ctx, session);
        LOG.debug("Successful login, redirecting to home page");
      }).orElseRun(error -> {
        try {
          ctx.status(HttpStatus.BAD_REQUEST);
          page(ctx, error);
          LOG.debug("Failed login, got error %s", error);
        } catch (Exception e) {}
      });
  }

  public void render(Context ctx) throws IOException {
    page(ctx, null);
  }

  private void page(Context ctx, ZoneBlitzError error) throws IOException {
    helper.template(
      ctx,
      new Login(
        Constants.GOOGLE_CLIENT_ID,
        String.format("%s/authenticate", WebConstants.URL),
        String.format("%s/gsi/client", Constants.GOOGLE_ACCOUNTS_URL),
        Constants.SHARED_AUTHENTICATION_FORM
      )
    );
    ctx.header(
      WebConstants.CONTENT_SECURITY_POLICY_HEADER_NAME,
      String.format(
        "%1$s %2$s; style-src 'self' %2$s 'unsafe-inline'",
        WebConstants.CONTENT_SECURITY_POLICY,
        Constants.GOOGLE_ACCOUNTS_URL
      )
    );
  }
}
