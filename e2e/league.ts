import { BrowserContext, Page } from "playwright";
import { expect } from "./expect";
import { lazyRegister } from "./auth";
import crypto from "node:crypto";

export const LEAGUES_URI = "/leagues";
export const CREATE_LEAGUE_URI = `${LEAGUES_URI}/create`;

export const createLeagueQueries = {
  getNameInput: (page: Page) => page.getByRole("textbox", { name: /name/i }),
  submit: (page: Page) => page.getByRole("button", { name: /create/i }).click(),
};

export const navigateToCreateLeague = async (
  page: Page,
  context: BrowserContext,
) => {
  await lazyRegister(page, context);
  await page.goto(CREATE_LEAGUE_URI);
  await expect(page.getByText(/create league/i)).toBeVisible();
};

export const navigateToLeagues = async (
  page: Page,
  context: BrowserContext,
) => {
  await lazyRegister(page, context);
  await page.goto(LEAGUES_URI);
  await expect(page.getByText(/your leagues/i)).toBeVisible();
};

export const createLeague = async (
  page: Page,
  context: BrowserContext,
  name = `LEAGUE-${crypto.randomUUID().toString()}`,
) => {
  await navigateToCreateLeague(page, context);
  await createLeagueQueries.getNameInput(page).fill(name);
  await createLeagueQueries.submit(page);
  await expect(page).toHaveURL(LEAGUES_URI);
  return {
    name,
  };
};
