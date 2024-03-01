package com.tiernebre;

import com.tiernebre.database.jooq.tables.Player;
import com.tiernebre.database.jooq.tables.records.PlayerRecord;
import com.tiernebre.web.ServerFactory;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class App {

  public static void main(String[] args) {
    new ServerFactory().create().start();
    try {
      var connection = DriverManager.getConnection(
        System.getenv("ZONE_BLITZ_POSTGRES_JDBC_URL"),
        System.getenv("ZONE_BLITZ_POSTGRES_USER"),
        System.getenv("ZONE_BLITZ_POSTGRES_PASSWORD")
      );
      var create = DSL.using(connection, SQLDialect.POSTGRES);
      var result = create
        .select()
        .from(Player.PLAYER)
        .fetchInto(PlayerRecord.class);
      System.out.println(result.size());
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
