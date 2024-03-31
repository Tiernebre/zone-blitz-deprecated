import { test } from "@playwright/test";
import { VALIDATION_MESSAGES, expect } from "./expect";
import crypto from "node:crypto";
import {
  CREATE_LEAGUE_URI,
  LEAGUES_URI,
  createLeague,
  createLeagueQueries,
  navigateToCreateLeague,
} from "./league";

const { getNameInput, submit } = createLeagueQueries;

test.beforeEach(async ({ page }) => {
  await navigateToCreateLeague(page);
});

test("renders for a logged in user", async ({ page }) => {
  await expect(page.getByText(/create league/i)).toBeVisible();
});

test("creates a league", async ({ page }) => {
  const name = `LEAGUE-${crypto.randomUUID().toString()}`;
  await createLeague(page, name, false);
  await expect(page).toHaveURL(LEAGUES_URI);
  await expect(page.getByText(/your leagues/i)).toBeVisible();
  await expect(page.getByText(name)).toBeVisible();
});

test("enforces maximum length on league name", async ({ page }) => {
  const name = "a".repeat(64);
  await getNameInput(page).fill(name + "a");
  await expect(getNameInput(page)).toHaveValue(name);
});

test("enforces that league name is required", async ({ page }) => {
  await submit(page);
  await expect(getNameInput(page)).toBeInvalid(VALIDATION_MESSAGES.REQUIRED);
  await expect(page).toHaveURL(CREATE_LEAGUE_URI);
});
