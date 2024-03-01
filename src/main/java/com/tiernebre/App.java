package com.tiernebre;

import com.tiernebre.jooq.tables.Player;
import com.tiernebre.web.ServerFactory;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class App {

  public static void main(String[] args) {
    new ServerFactory().create().start();
  }
}
