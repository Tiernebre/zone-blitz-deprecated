package com.tiernebre;

import com.tiernebre.web.DependencyContextFactory;
import com.tiernebre.web.ServerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

  private static final Logger LOG = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    try {
      new ServerFactory(new DependencyContextFactory().create())
        .create()
        .start();
    } catch (Exception e) {
      LOG.error(
        String.format(
          "Fatal error occurred when creating the application: %s.\n\nExiting Zone Blitz application startup with an error code.",
          e
        )
      );
      System.exit(1);
    }
  }
}
