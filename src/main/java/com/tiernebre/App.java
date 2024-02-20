package com.tiernebre;

import com.tiernebre.web.Router;
import com.tiernebre.web.Server;

public class App {

  public static void main(String[] args) {
    new Server(new Router()).start();
  }
}
