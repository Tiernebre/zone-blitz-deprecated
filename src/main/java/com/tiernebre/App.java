package com.tiernebre;

import com.tiernebre.web.Router;
import io.javalin.Javalin;

public class App {

  public static void main(String[] args) {
    new Router()
      .register(
        Javalin.create(config -> {
          config.staticFiles.add(staticFiles -> {
            staticFiles.hostedPath = "/assets";
            staticFiles.directory = "/assets";
          });
        })
      )
      .start(8000);
  }
}
