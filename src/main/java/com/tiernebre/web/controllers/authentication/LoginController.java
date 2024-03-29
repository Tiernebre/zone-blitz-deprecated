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
        ctx.status(error.httpStatus());
        page(ctx, error.publicMessage());
        LOG.debug("Failed login, got error {}", error);
      });
  }

  public void render(Context ctx) {
    page(ctx, null);
  }

  private void page(Context ctx, String error) {
    helper.template(ctx, new Login(error, warning(ctx)));
    AuthenticationWebControllerHelper.allowGoogleScript(ctx);
  }

  private String warning(Context ctx) {
    return ctx
        .queryParamMap()
        .keySet()
        .contains(WebConstants.LOGGED_OUT_QUERY_PARAM)
      ? "The page or action you performed requires you to be logged in. Please log in again or register if you haven't made an account yet."
      : null;
  }
}
