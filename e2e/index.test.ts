import { test } from "playwright/test";
import { expect } from "./expect";

test("does not have detectable accessibility issues", async ({ page }) => {
  await page.goto("/");
  await expect(page).toBeAccessible();
});
