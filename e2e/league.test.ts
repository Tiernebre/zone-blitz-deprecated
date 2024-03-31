import { test } from "@playwright/test";
import { register } from "./auth";
import { expect } from "./expect";

test("renders for an existing league", async ({ page }) => {
  await register(page);
  await page.goto("/leagues");
  await expect(page.getByText(/your leagues/i)).toBeVisible();
});
