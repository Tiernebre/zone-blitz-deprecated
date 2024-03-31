import { test } from "@playwright/test";
import { navigateToLeagues } from "./league";

test("renders for a logged in user", async ({ page, context }) => {
  await navigateToLeagues(page, context);
});
