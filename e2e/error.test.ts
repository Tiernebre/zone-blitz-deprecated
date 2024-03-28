import { expect, test } from "@playwright/test";
import crypto from "node:crypto";

test("not found page", async ({ page }) => {
  const notFoundPage = await page.goto(
    `/non-existent-uri/${crypto.randomUUID().toString()}`,
  );
  expect(notFoundPage?.status()).toStrictEqual(404);
  await page.screenshot({ path: "test-results/error.png" });
  await expect(
    page.getByText(/requested page could not be found/i),
  ).toBeVisible();
  await expect(page.getByText(/Zone Blitz/i)).toBeVisible();
});
