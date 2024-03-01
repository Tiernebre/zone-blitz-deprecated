package com.tiernebre;

import com.tiernebre.web.ServerFactory;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

  public static void main(String[] args) {
    new ServerFactory().create().start();
    try {
      var connection = DriverManager.getConnection(
        System.getenv("ZONE_BLITZ_POSTGRES_JDBC_URL"),
        System.getenv("ZONE_BLITZ_POSTGRES_USER"),
        System.getenv("ZONE_BLITZ_POSTGRES_PASSWORD")
      );
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
