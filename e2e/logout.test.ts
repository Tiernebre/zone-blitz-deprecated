import test, { Page } from "playwright/test";
import crypto from "node:crypto";
import { register } from "./helpers";
import { expect } from "./expect";

const getLogoutButton = (page: Page) =>
  page.getByRole("button", { name: /logout/i });

test("logs a user out", async ({ page }) => {
  const username = `LOGOUT-${crypto.randomUUID().toString()}`;
  const password = `LOGOUT-${crypto.randomUUID().toString()}`;
  await register(page, username, password);
  await expect(page).toBeLoggedIn();
  await getLogoutButton(page).click();
  await expect(page).toBeLoggedOut();
});
