import AxeBuilder from "@axe-core/playwright";
import { expect, test } from "playwright/test";

test("does not have detectable accessibility issues", async ({ page }) => {
  await page.goto("/");
  const { violations } = await new AxeBuilder({ page }).analyze();
  expect(violations).toHaveLength(0);
});
