package com.tiernebre.web.e2e;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.regex.Pattern;
import org.junit.Test;

public class HelloE2ETest extends E2EHttpSuite {

  @Test
  public void hello() {
    var page = BROWSER.newPage();
    page.navigate("https://playwright.dev/");
    assertThat(page).hasTitle(Pattern.compile("Playwright"));
  }
}
