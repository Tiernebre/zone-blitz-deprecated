import { test } from "@playwright/test";
import { expect } from "./expect";
import { createLeague, navigateToLeagues } from "./league";

test("renders for an existing league", async ({ page, context }) => {
  await createLeague(page, context);
  await navigateToLeagues(page, context);
  await expect(page.getByText(/your leagues/i)).toBeVisible();
});
