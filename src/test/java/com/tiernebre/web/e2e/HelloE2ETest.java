package com.tiernebre.web.e2e;

import com.microsoft.playwright.*;
import com.tiernebre.web.WebHttpTestUtils;
import io.javalin.Javalin;
import java.nio.file.Paths;
import org.junit.Test;

public class HelloE2ETest {

  Javalin server = WebHttpTestUtils.startServer();

  @Test
  public void hello() {
    try (Playwright playwright = Playwright.create()) {
      var options = new BrowserType.LaunchOptions()
        .setHeadless(true)
        .setExecutablePath(
          Paths.get(System.getenv("PLAYWRIGHT_CHROMIUM_EXECUTABLE_PATH"))
        );
      Browser browser = playwright.chromium().launch(options);
      Page page = browser.newPage();
      page.navigate("https://playwright.dev/");
    }
  }
}
