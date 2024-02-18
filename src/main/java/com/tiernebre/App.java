package com.tiernebre;

import com.tiernebre.templates.Hello;
import io.javalin.Javalin;
import io.jstach.jstachio.JStachio;

/**
 * Hello world!
 *
 */
public class App {

  public static void main(String[] args) {
    Javalin
      .create(config -> {
        config.staticFiles.add(staticFiles -> {
          staticFiles.hostedPath = "/assets";
          staticFiles.directory = "/assets";
        });
      })
      .get(
        "/",
        ctx -> {
          var hello = new Hello();
          var output = new StringBuilder();
          JStachio.render(hello, output);
          ctx.html(output.toString());
        }
      )
      .start(8000);
  }
}
