package com.tiernebre;

import com.tiernebre.web.ServerFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class App {

  public static void main(String[] args)
    throws GeneralSecurityException, IOException {
    new ServerFactory().create().start();
  }
}
