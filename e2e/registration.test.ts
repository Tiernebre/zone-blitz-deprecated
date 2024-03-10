import { test, expect } from "@playwright/test";

test("GET /registrations", async ({ page }) => {
  const registrationsPage = await page.goto("/registration");
  expect(registrationsPage).not.toBeNull();
  expect(registrationsPage!.status()).toStrictEqual(200);
});
