package com.tiernebre.web.controllers;

import com.tiernebre.web.constants.WebConstants;
import com.tiernebre.web.templates.RegistrationPage;
import io.javalin.http.Context;
import io.jstach.jstachio.JStachio;

public class RegistrationPageController {

  public void render(Context ctx) {
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
