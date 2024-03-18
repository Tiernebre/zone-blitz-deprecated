import { Page } from "playwright";
import { expect } from "./expect";

export const getHeaderLoginButton = (page: Page) =>
  page.locator("header").getByRole("button", { name: /login/i });
export const getHeaderRegisterButton = (page: Page) =>
  page.locator("header").getByRole("button", { name: /register/i });
export const getHeaderLogoutButton = (page: Page) =>
  page.locator("header").getByRole("button", { name: /logout/i });

export const expectToBeLoggedIn = async (page: Page) => {
  await expect(getHeaderLogoutButton(page)).toBeVisible();
  await expect(getHeaderLoginButton(page)).not.toBeVisible();
  await expect(getHeaderRegisterButton(page)).not.toBeVisible();
};
