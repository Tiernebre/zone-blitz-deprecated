import { test, expect, Page } from "@playwright/test";
import crypto from "node:crypto";

const URI = "/registration";
const PASSWORD = crypto.randomUUID();

const getUsernameInput = (page: Page) =>
  page.getByRole("textbox", { name: /Username/i });

test.beforeEach(async ({ page }) => {
  await page.goto(URI);
});

test("registration page exists", async ({ page }) => {
  const registrationsPage = await page.goto(URI);
  expect(registrationsPage).not.toBeNull();
  expect(registrationsPage!.status()).toStrictEqual(200);
});

test("registers a user", async ({ page }) => {
  await getUsernameInput(page).fill(crypto.randomUUID());
  await page.getByLabel("Password", { exact: true }).fill(PASSWORD);
  await page.getByLabel("Confirm Password", { exact: true }).fill(PASSWORD);
  await page.getByRole("button", { name: /Register/i }).click();
  await page.waitForURL("/");
  expect(page.url()).not.toContain("registration");
});

test("requires a username", async ({ page }) => {
  const password = crypto.randomUUID();
  await page.getByLabel("Password", { exact: true }).fill(password);
  await page.getByLabel("Confirm Password", { exact: true }).fill(password);
  await page.getByRole("button", { name: /Register/i }).click();
  expect(
    await getUsernameInput(page).evaluate(
      (node) =>
        node.matches(":invalid") &&
        node instanceof HTMLInputElement &&
        node.validationMessage === "Please fill out this field.",
    ),
  ).toStrictEqual(true);
});
