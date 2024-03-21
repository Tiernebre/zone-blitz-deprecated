package com.tiernebre.web.controllers.authentication;

import com.tiernebre.authentication.google.GoogleAuthenticationRequest;
import com.tiernebre.authentication.google.GoogleAuthenticationStrategy;
import com.tiernebre.authentication.registration.RegistrationAuthenticationRequest;
import com.tiernebre.authentication.registration.RegistrationAuthenticationStrategy;
import com.tiernebre.web.constants.WebConstants;
import com.tiernebre.web.templates.Login;
import com.tiernebre.web.util.SessionRegistry;
import com.tiernebre.web.util.WebHelper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoginController {

  private final Logger LOG = LoggerFactory.getLogger(LoginController.class);

  private final WebHelper helper;
  private final GoogleAuthenticationStrategy googleAuthenticationStrategy;
  private final RegistrationAuthenticationStrategy registrationAuthenticationStrategy;
  private final SessionRegistry sessionRegister;

  public LoginController(
    WebHelper helper,
    GoogleAuthenticationStrategy googleAuthenticationStrategy,
    RegistrationAuthenticationStrategy registrationAuthenticationStrategy,
    SessionRegistry sessionRegister
  ) {
    this.helper = helper;
    this.googleAuthenticationStrategy = googleAuthenticationStrategy;
    this.registrationAuthenticationStrategy =
      registrationAuthenticationStrategy;
    this.sessionRegister = sessionRegister;
  }

  public void handle(Context ctx) {
    (StringUtils.isBlank(
          ctx.cookie(AuthenticationWebConstants.GOOGLE_CSRF_TOKEN_FIELD_NAME)
        )
        ? registrationAuthenticationStrategy.authenticate(
          new RegistrationAuthenticationRequest(
            ctx.formParam(AuthenticationWebConstants.USERNAME_PARAMETER),
            ctx.formParam(AuthenticationWebConstants.PASSWORD_PARAMETER)
          )
        )
        : googleAuthenticationStrategy.authenticate(
          new GoogleAuthenticationRequest(
            ctx.formParam(
              AuthenticationWebConstants.GOOGLE_CREDENTIAL_FIELD_NAME
            ),
            ctx.formParam(
              AuthenticationWebConstants.GOOGLE_CSRF_TOKEN_FIELD_NAME
            ),
            ctx.cookie(AuthenticationWebConstants.GOOGLE_CSRF_TOKEN_FIELD_NAME)
          )
        )).peek(session -> {
        sessionRegister.register(ctx, session);
        ctx.redirect("/");
        LOG.debug("Successful login, redirecting to home page");
      }).orElseRun(error -> {
        try {
          ctx.status(HttpStatus.BAD_REQUEST);
          page(ctx, error.publicMessage());
          LOG.debug("Failed login, got error {}", error);
        } catch (Exception e) {
          ctx.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
      });
  }

  public void render(Context ctx) throws IOException {
    page(ctx, null);
  }

  private void page(Context ctx, String error) throws IOException {
    helper.template(
      ctx,
      new Login(
        AuthenticationWebConstants.GOOGLE_CLIENT_ID,
        String.format("%s/login", WebConstants.URL),
        String.format(
          "%s/gsi/client",
          AuthenticationWebConstants.GOOGLE_ACCOUNTS_URL
        ),
        AuthenticationWebConstants.SHARED_AUTHENTICATION_FORM,
        error
      )
    );
    ctx.header(
      WebConstants.CONTENT_SECURITY_POLICY_HEADER_NAME,
      String.format(
        "%1$s %2$s; style-src 'self' %2$s 'unsafe-inline'",
        WebConstants.CONTENT_SECURITY_POLICY,
        AuthenticationWebConstants.GOOGLE_ACCOUNTS_URL
      )
    );
  }
}
