package com.tiernebre;

import com.tiernebre.web.ServerFactory;

public class App {

  public static void main(String[] args) {
    new ServerFactory().create().start();
  }
}
