import { test, Page } from "@playwright/test";
import crypto from "node:crypto";
import { VALIDATION_MESSAGES, expect } from "./expect";

const URI = "/registration";
const PASSWORD = crypto.randomUUID().toString();

const getUsernameInput = (page: Page) =>
  page.getByRole("textbox", { name: /Username/i });
const getPasswordInput = (page: Page) =>
  page.getByLabel("Password", { exact: true });
const getConfirmPasswordInput = (page: Page) =>
  page.getByLabel("Confirm Password", { exact: true });
const submit = (page: Page) =>
  page.getByRole("button", { name: /Register/i }).click();

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
  await getPasswordInput(page).fill(PASSWORD);
  await getConfirmPasswordInput(page).fill(PASSWORD);
  await submit(page);
  await expect(page).not.toHaveURL(/.*registration/);
});

test("requires a username", async ({ page }) => {
  await getPasswordInput(page).fill(PASSWORD);
  await getConfirmPasswordInput(page).fill(PASSWORD);
  await submit(page);
  await expect(getUsernameInput(page)).toBeInvalid(
    VALIDATION_MESSAGES.REQUIRED,
  );
  await expect(getPasswordInput(page)).toBeValid();
  await expect(getConfirmPasswordInput(page)).toBeValid();
});

test("validates that the username cannot be a single space", async ({
  page,
}) => {
  await getUsernameInput(page).fill(" ");
  await getPasswordInput(page).fill(PASSWORD);
  await getConfirmPasswordInput(page).fill(PASSWORD);
  await submit(page);
  await expect(getUsernameInput(page)).toBeInvalid(VALIDATION_MESSAGES.PATTERN);
});

test("enforces maximum length on a username", async ({ page }) => {
  const username = "a".repeat(64);
  await getUsernameInput(page).fill(username + "b");
  await expect(getUsernameInput(page)).toHaveValue(username);
});

test("requires a password", async ({ page }) => {
  await getUsernameInput(page).fill(crypto.randomUUID().toString());
  await getConfirmPasswordInput(page).fill(PASSWORD);
  await submit(page);
  await expect(getUsernameInput(page)).toBeValid();
  await expect(getPasswordInput(page)).toBeInvalid(
    VALIDATION_MESSAGES.REQUIRED,
  );
  await expect(getConfirmPasswordInput(page)).toBeValid();
});

test("enforces minimum length on a password", async ({ page }) => {
  await getUsernameInput(page).fill(crypto.randomUUID().toString());
  await getPasswordInput(page).fill("a");
  await submit(page);
  await expect(getUsernameInput(page)).toBeValid();
  await expect(getPasswordInput(page)).toBeInvalid(
    VALIDATION_MESSAGES.MINLENGTH(8),
  );
});

test("enforces maximum length on a password", async ({ page }) => {
  const password = "a".repeat(64);
  await getUsernameInput(page).fill(password + "b");
  await expect(getUsernameInput(page)).toHaveValue(password);
});

test("enforces minimum length on confirm password", async ({ page }) => {
  await getUsernameInput(page).fill(crypto.randomUUID().toString());
  await getPasswordInput(page).fill(PASSWORD);
  await getConfirmPasswordInput(page).fill("a");
  await submit(page);
  await expect(getUsernameInput(page)).toBeValid();
  await expect(getPasswordInput(page)).toBeValid();
  await expect(getConfirmPasswordInput(page)).toBeInvalid(
    VALIDATION_MESSAGES.MINLENGTH(8),
  );
});

test("enforces maximum length on confirm password", async ({ page }) => {
  const password = "a".repeat(64);
  await getConfirmPasswordInput(page).fill(password + "b");
  await expect(getConfirmPasswordInput(page)).toHaveValue(password);
});

test("requires a confirm password", async ({ page }) => {
  await getUsernameInput(page).fill(crypto.randomUUID().toString());
  await getPasswordInput(page).fill(PASSWORD);
  await submit(page);
  await expect(getUsernameInput(page)).toBeValid();
  await expect(getPasswordInput(page)).toBeValid();
  await expect(getConfirmPasswordInput(page)).toBeInvalid(
    VALIDATION_MESSAGES.REQUIRED,
  );
});
