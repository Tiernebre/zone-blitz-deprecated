import test from "playwright/test";
import crypto from "node:crypto";
import { logout, register } from "./helpers";
import { expect } from "./expect";

test("logs a user out", async ({ context, page }) => {
  const username = `LOGOUT-${crypto.randomUUID().toString()}`;
  const password = `LOGOUT-${crypto.randomUUID().toString()}`;
  await register(page, username, password);
  await expect({ context, page }).toBeLoggedIn();
  await logout(page);
  await expect({ context, page }).toBeLoggedOut();
});
