package com.tiernebre.web;

import com.tiernebre.web.constants.WebConstants;
import com.tiernebre.web.errors.Errors;
import com.tiernebre.web.guards.Guards;
import com.tiernebre.web.middlewares.Middlewares;
import com.tiernebre.web.routes.Routes;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Server {

  private static final Logger LOG = LoggerFactory.getLogger(Server.class);

  private final Routes routes;
  private final Middlewares middlewares;
  private final Guards guards;
  private final Errors errors;

  public Server(
    Routes routes,
    Middlewares middlewares,
    Guards guards,
    Errors errors
  ) {
    this.routes = routes;
    this.middlewares = middlewares;
    this.guards = guards;
    this.errors = errors;
  }

  public Javalin start() {
    return start(8000);
  }

  public Javalin start(int port) {
    var server = Javalin.create(config -> {
      config.showJavalinBanner = false;
      config.staticFiles.add(staticFiles -> {
        staticFiles.hostedPath = "/";
        staticFiles.directory = "/assets";
      });
      config.router.apiBuilder(routes);
    });
    middlewares.register(server);
    guards.register(server);
    errors.register(server);
    server = server.start("0.0.0.0", port);
    LOG.debug(
      "DEVELOPERS: Ignore the 'listening' log above. Access Zone Blitz through proxy server at {}",
      WebConstants.URL
    );
    return server;
  }
}
