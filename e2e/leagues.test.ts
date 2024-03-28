import { test } from "@playwright/test";
import { register } from "./helpers";
import { expect } from "./expect";

test("renders for a logged in user", async ({ page }) => {
  await register(page);
  await page.goto("/leagues");
  await expect(page.getByText(/your leagues/i)).toBeVisible();
});
