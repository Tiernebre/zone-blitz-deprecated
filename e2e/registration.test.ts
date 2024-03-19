import { test } from "@playwright/test";
import crypto from "node:crypto";
import { VALIDATION_MESSAGES, expect } from "./expect";
import { registrationQueries } from "./helpers";

const URI = "/registration";
const USERNAME = `REGISTER-${crypto.randomUUID().toString()}`;
const PASSWORD = `REGISTER-${crypto.randomUUID().toString()}`;

test.beforeEach(async ({ page }) => {
  await page.goto(URI);
});

const {
  getUsernameInput,
  getPasswordInput,
  getConfirmPasswordInput,
  clickRegisterButton: submit,
} = registrationQueries;

test("registration page exists", async ({ page }) => {
  const registrationsPage = await page.goto(URI);
  expect(registrationsPage).not.toBeNull();
  expect(registrationsPage!.status()).toStrictEqual(200);
});

test("registers a user", async ({ page }) => {
  await getUsernameInput(page).fill(USERNAME);
  await getPasswordInput(page).fill(PASSWORD);
  await getConfirmPasswordInput(page).fill(PASSWORD);
  await submit(page);
  await expect(page).not.toHaveURL(/.*registration/);
  await expect(page).toBeLoggedIn();
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
  await expect(page).toBeLoggedOut();
});

test("validates that the username cannot be a single space", async ({
  page,
}) => {
  await getUsernameInput(page).fill(" ");
  await getPasswordInput(page).fill(PASSWORD);
  await getConfirmPasswordInput(page).fill(PASSWORD);
  await submit(page);
  await expect(getUsernameInput(page)).toBeInvalid(VALIDATION_MESSAGES.PATTERN);
  await expect(page).toBeLoggedOut();
});

test("enforces maximum length on a username", async ({ page }) => {
  const username = "a".repeat(64);
  await getUsernameInput(page).fill(username + "b");
  await expect(getUsernameInput(page)).toHaveValue(username);
  await expect(page).toBeLoggedOut();
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
  await expect(page).toBeLoggedOut();
});

test("enforces minimum length on a password", async ({ page }) => {
  await getUsernameInput(page).fill(crypto.randomUUID().toString());
  await getPasswordInput(page).fill("a");
  await submit(page);
  await expect(getUsernameInput(page)).toBeValid();
  await expect(getPasswordInput(page)).toBeInvalid(
    VALIDATION_MESSAGES.MINLENGTH(8),
  );
  await expect(page).toBeLoggedOut();
});

test("enforces maximum length on a password", async ({ page }) => {
  const password = "a".repeat(64);
  await getPasswordInput(page).fill(password + "b");
  await expect(getPasswordInput(page)).toHaveValue(password);
  await expect(page).toBeLoggedOut();
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
  await expect(page).toBeLoggedOut();
});

test("enforces maximum length on confirm password", async ({ page }) => {
  const password = "a".repeat(64);
  await getConfirmPasswordInput(page).fill(password + "b");
  await expect(getConfirmPasswordInput(page)).toHaveValue(password);
  await expect(page).toBeLoggedOut();
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
  await expect(page).toBeLoggedOut();
});

test("validates that password must match confirm password", async ({
  page,
}) => {
  await getUsernameInput(page).fill(crypto.randomUUID());
  await getPasswordInput(page).fill(PASSWORD);
  await getConfirmPasswordInput(page).fill(PASSWORD + "a");
  await submit(page);
  await expect(page).toHaveURL(/.*registration/);
  await expect(
    page.getByText(/password must match confirm password/i),
  ).toBeVisible();
  await expect(page).toBeLoggedOut();
});

test("validates that a duplicate username cannot be created", async ({
  page,
}) => {
  const username = `D-${USERNAME}`;
  await getUsernameInput(page).fill(username);
  await getPasswordInput(page).fill(PASSWORD);
  await getConfirmPasswordInput(page).fill(PASSWORD);
  await submit(page);
  await expect(page).not.toHaveURL(/.*registration/);
  await expect(page).toBeLoggedIn();
  await page.goto(URI);
  await getUsernameInput(page).fill(username);
  await getPasswordInput(page).fill(PASSWORD);
  await getConfirmPasswordInput(page).fill(PASSWORD);
  await submit(page);
  await expect(page.getByText(/username already exists/i)).toBeVisible();
  await expect(page).toBeLoggedOut();
});
