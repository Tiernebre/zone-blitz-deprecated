package com.tiernebre.web.controllers;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

public class HealthController {

  public void health(Context ctx) {
    ctx.status(HttpStatus.OK);
    ctx.result("Healthy updated again");
  }
}
