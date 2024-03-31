import { Page, test } from "@playwright/test";
import crypto from "node:crypto";
import { VALIDATION_MESSAGES, expect } from "./expect";
import { LEAGUES_URI } from "./league";

const URI = "/login";
const USERNAME = `LOGIN-${crypto.randomUUID().toString()}`;
const PASSWORD = `LOGIN-${crypto.randomUUID().toString()}`;

const NO_USER_ERROR_MESSAGE =
  /Could not find a registration with the given username and password/i;

const getUsernameInput = (page: Page) =>
  page.getByRole("textbox", { name: /Username/i });
const getPasswordInput = (page: Page) =>
  page.getByLabel("Password", { exact: true });
const submit = (page: Page) =>
  page.getByRole("button", { name: /Login/i }).click();
const getNoUserExistsError = (page: Page) =>
  page.getByText(NO_USER_ERROR_MESSAGE);

test.beforeAll(async ({ request }) => {
  const response = await request.post("/registration", {
    headers: {
      "Content-Type": "x-www-form-urlencoded",
    },
    data: `username=${USERNAME}&password=${PASSWORD}&confirmPassword=${PASSWORD}`,
  });
  expect(response.status()).toStrictEqual(200);
});

test.beforeEach(async ({ page }) => {
  await page.goto(URI);
});

test("renders", async ({ page }) => {
  const loginPage = await page.goto(URI);
  expect(loginPage).not.toBeNull();
  expect(loginPage!.status()).toStrictEqual(200);
});

test("is accessible", async ({ page }) => {
  await expect(page).toBeAccessible();
});

test("logs the user in", async ({ context, page }) => {
  await getUsernameInput(page).fill(USERNAME);
  await getPasswordInput(page).fill(PASSWORD);
  await submit(page);
  await expect(page).not.toHaveURL(/login/i);
  await expect(page).toHaveURL("/");
  await expect({ context, page }).toBeLoggedIn();
});

test("validates that the username is required", async ({ context, page }) => {
  await getPasswordInput(page).fill(PASSWORD);
  await submit(page);
  await expect(page).toHaveURL(/login/i);
  await expect(getUsernameInput(page)).toBeInvalid(
    VALIDATION_MESSAGES.REQUIRED,
  );
  await expect({ context, page }).toBeLoggedOut();
});

test("enforces maximum length on a username", async ({ context, page }) => {
  const username = "a".repeat(64);
  await getUsernameInput(page).fill(username + "b");
  await expect(getUsernameInput(page)).toHaveValue(username);
  await expect({ context, page }).toBeLoggedOut();
});

test("validates that the password is required", async ({ context, page }) => {
  await getUsernameInput(page).fill(USERNAME);
  await submit(page);
  await expect(page).toHaveURL(/login/i);
  await expect(getPasswordInput(page)).toBeInvalid(
    VALIDATION_MESSAGES.REQUIRED,
  );
  await expect({ context, page }).toBeLoggedOut();
});

test("enforces minimum length on a password", async ({ context, page }) => {
  await getUsernameInput(page).fill(crypto.randomUUID().toString());
  await getPasswordInput(page).fill("a");
  await submit(page);
  await expect(getUsernameInput(page)).toBeValid();
  await expect(getPasswordInput(page)).toBeInvalid(
    VALIDATION_MESSAGES.MINLENGTH(8),
  );
  await expect({ context, page }).toBeLoggedOut();
});

test("enforces maximum length on a password", async ({ context, page }) => {
  const password = "a".repeat(64);
  await getPasswordInput(page).fill(password + "b");
  await expect(getPasswordInput(page)).toHaveValue(password);
  await expect({ context, page }).toBeLoggedOut();
});

test("does not login for a username that does not exist", async ({
  context,
  page,
}) => {
  await getUsernameInput(page).fill("doesnotexist");
  await getPasswordInput(page).fill(PASSWORD);
  await submit(page);
  await expect(page).toHaveURL(/login/i);
  await expect(getNoUserExistsError(page)).toBeVisible();
  await expect({ context, page }).toBeLoggedOut();
});

test("does not login for a password that does not exist", async ({
  context,
  page,
}) => {
  await getUsernameInput(page).fill(USERNAME);
  await getPasswordInput(page).fill("notthecorrectpassword");
  await submit(page);
  await expect(page).toHaveURL(/login/i);
  await expect(getNoUserExistsError(page)).toBeVisible();
  await expect({ context, page }).toBeLoggedOut();
});

test("is used when a user accesses a page they are not authenticated for", async ({
  context,
  page,
}) => {
  await expect({ context, page }).toBeLoggedOut();
  const originalPath = LEAGUES_URI;
  await page.goto(originalPath);
  await expect(page).toHaveURL(/login/i);
  await expect(page.getByText(/requires you to be logged in/)).toBeVisible();
  await getUsernameInput(page).fill(USERNAME);
  await getPasswordInput(page).fill(PASSWORD);
  await submit(page);
  await expect(page).not.toHaveURL(/login/i);
  await expect(page).toHaveURL(originalPath);
  await expect({ context, page }).toBeLoggedIn();
});
