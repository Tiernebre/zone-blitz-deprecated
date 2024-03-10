package com.tiernebre.web.e2e;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HelloE2ETest extends E2EHttpSuite {

  @Test
  public void hello() {
    var page = BROWSER.newPage();
    page.navigate("https://playwright.dev/");
    assertTrue(page.title().contains("Playwright"));
  }
}
