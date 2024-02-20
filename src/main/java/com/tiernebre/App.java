package com.tiernebre;

import com.tiernebre.web.Router;
import com.tiernebre.web.ZoneBlitzWebServer;

public class App {

  public static void main(String[] args) {
    new ZoneBlitzWebServer(new Router()).start();
  }
}
