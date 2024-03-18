import { test } from "@playwright/test";
import crypto from "node:crypto";
import { expect } from "./expect";

const URI = "/login";
const USERNAME = crypto.randomUUID().toString();
const PASSWORD = crypto.randomUUID().toString();

test.beforeAll(async ({ request }) => {
  const response = await request.post("/registration", {
    headers: {
      "Content-Type": "x-www-form-urlencoded",
    },
    data: `username=${USERNAME}&password=${PASSWORD}&confirmPassword=${PASSWORD}`,
  });
  expect(response.status()).toStrictEqual(200);
});

test("renders the login page", async ({ page }) => {
  const loginsPage = await page.goto(URI);
  expect(loginsPage).not.toBeNull();
  expect(loginsPage!.status()).toStrictEqual(200);
});
