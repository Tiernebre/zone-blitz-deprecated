import { Page, test } from "@playwright/test";
import crypto from "node:crypto";
import { expect } from "./expect";
import { expectToBeLoggedIn } from "./common";

const URI = "/login";
const USERNAME = `LOGIN-${crypto.randomUUID().toString()}`;
const PASSWORD = `LOGIN-${crypto.randomUUID().toString()}`;

const getUsernameInput = (page: Page) =>
  page.getByRole("textbox", { name: /Username/i });
const getPasswordInput = (page: Page) =>
  page.getByLabel("Password", { exact: true });
const submit = (page: Page) =>
  page.getByRole("button", { name: /Login/i }).click();

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

test("renders the login page", async ({ page }) => {
  const loginPage = await page.goto(URI);
  expect(loginPage).not.toBeNull();
  expect(loginPage!.status()).toStrictEqual(200);
});

test("logs the user in", async ({ page }) => {
  await getUsernameInput(page).fill(USERNAME);
  await getPasswordInput(page).fill(PASSWORD);
  await submit(page);
  await page.screenshot({ path: "test-results/login.png" });
  await expect(page).not.toHaveURL(/login/i);
  await expectToBeLoggedIn(page);
});
