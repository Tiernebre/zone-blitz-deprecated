import { test } from "@playwright/test";
import { register } from "./auth";
import { expect } from "./expect";
import { LEAGUES_URI } from "./league";

test("renders for an existing league", async ({ page }) => {
  await register(page);
  await page.goto(LEAGUES_URI);
  await expect(page.getByText(/your leagues/i)).toBeVisible();
});
