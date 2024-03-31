import { Page } from "playwright";
import { expect } from "./expect";
import { register } from "./auth";

export const LEAGUES_URI = "/leagues";
export const CREATE_LEAGUE_URI = `${LEAGUES_URI}/create`;

export const createLeagueQueries = {
  getNameInput: (page: Page) => page.getByRole("textbox", { name: /name/i }),
  submit: (page: Page) => page.getByRole("button", { name: /create/i }).click(),
};

export const navigateToCreateLeague = async (page: Page) => {
  await register(page);
  await page.goto(CREATE_LEAGUE_URI);
};

export const createLeague = async (
  page: Page,
  name = `LEAGUE-${crypto.randomUUID().toString()}`,
  navigate = true,
) => {
  if (navigate) {
    await navigateToCreateLeague(page);
  }
  await createLeagueQueries.getNameInput(page).fill(name);
  await createLeagueQueries.submit(page);
  await expect(page).toHaveURL(LEAGUES_URI);
};
