package com.tiernebre.web.e2e;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.tiernebre.context.DependencyContextFactory;
import com.tiernebre.web.ServerFactory;
import io.javalin.Javalin;
import java.nio.file.Paths;

public class E2EHttpSuite {

  protected static Javalin SERVER = null;
  protected static Browser BROWSER = null;

  public E2EHttpSuite() {
    startServer();
    createBrowser();
  }

  private static void startServer() {
    if (SERVER == null) {
      try {
        SERVER = new ServerFactory(new DependencyContextFactory().create())
          .create()
          .start(49152);
      } catch (Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  private static void createBrowser() {
    try {
      Playwright playwright = Playwright.create();
      var options = new BrowserType.LaunchOptions()
        .setHeadless(true)
        .setExecutablePath(
          Paths.get(System.getenv("PLAYWRIGHT_CHROMIUM_EXECUTABLE_PATH"))
        );
      BROWSER = playwright.chromium().launch(options);
    } catch (Exception e) {
      throw new AssertionError(e);
    }
  }

  protected String url(String path) {
    return String.format("http://0.0.0.0:%s%s", SERVER.port(), path);
  }
}
