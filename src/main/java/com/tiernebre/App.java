package com.tiernebre;

import com.tiernebre.web.ServerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    try {
      new ServerFactory().create().start();
    } catch (Exception e) {
      logger.error(
        String.format(
          "Fatal error occurred when creating the application: %s.\n\nExiting Zone Blitz application startup with an error code.",
          e
        )
      );
      System.exit(1);
    }
  }
}
