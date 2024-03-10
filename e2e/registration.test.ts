import { test, expect } from "@playwright/test";
import crypto from "node:crypto";

test("registers a user", async ({ page }) => {
  const registrationsPage = await page.goto("/registration");
  expect(registrationsPage).not.toBeNull();
  expect(registrationsPage!.status()).toStrictEqual(200);
  const password = crypto.randomUUID();
  await page
    .getByRole("textbox", { name: /Username/i })
    .fill(crypto.randomUUID());
  await page.getByLabel("Password", { exact: true }).fill(password);
  await page.getByLabel("Confirm Password", { exact: true }).fill(password);
  await page.getByRole("button", { name: /Register/i }).click();
  await page.waitForURL("/");
  expect(page.url()).not.toContain("registration");
});
