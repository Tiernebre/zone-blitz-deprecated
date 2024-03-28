import { expect, test } from "@playwright/test";
import crypto from "node:crypto";

test("not found page", async ({ page }) => {
  const notFoundPage = await page.goto(
    `/non-existent-uri/${crypto.randomUUID().toString()}`,
  );
  expect(notFoundPage?.status()).toStrictEqual(404);
  await expect(
    page.getByText(/requested page could not be found/i),
  ).toBeVisible();
  await expect(page.getByText(/Zone Blitz/i)).toBeVisible();
});

test("unauthorized handling", async ({ page }) => {
  await page.goto("/leagues");
  await expect(page).toHaveURL(/login/i);
});
