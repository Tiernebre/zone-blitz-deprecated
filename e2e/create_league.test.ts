import { Page, test } from "@playwright/test";
import { register } from "./helpers";
import { expect } from "./expect";
import crypto from "node:crypto";

const getNameInput = (page: Page) =>
  page.getByRole("textbox", { name: /name/i });
const submit = (page: Page) =>
  page.getByRole("button", { name: /create/i }).click();

test("renders for a logged in user", async ({ page }) => {
  await register(page);
  await page.goto("/leagues/create");
  await expect(page.getByText(/create league/i)).toBeVisible();
});

test("creates a league", async ({ page }) => {
  await register(page);
  await page.goto("/leagues/create");
  const name = `LEAGUE-${crypto.randomUUID().toString()}`;
  await getNameInput(page).fill(name);
  await submit(page);
  await expect(page).toHaveURL("/leagues");
  await expect(page.getByText(/your leagues/i)).toBeVisible();
  await expect(page.getByText(name)).toBeVisible();
});

test("enforces maximum length on league name", async ({ page }) => {
  await register(page);
  await page.goto("/leagues/create");
  const name = "a".repeat(64);
  await getNameInput(page).fill(name + "a");
  await expect(getNameInput(page)).toHaveValue(name);
});
