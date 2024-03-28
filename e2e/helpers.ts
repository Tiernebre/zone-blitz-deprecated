import { Page } from "playwright";
import { expect } from "playwright/test";
import crypto from "node:crypto";

export const registrationQueries = {
  getUsernameInput: (page: Page) =>
    page.getByRole("textbox", { name: /Username/i }),
  getPasswordInput: (page: Page) =>
    page.getByLabel("Password", { exact: true }),
  getConfirmPasswordInput: (page: Page) =>
    page.getByLabel("Confirm Password", { exact: true }),
  clickRegisterButton: (page: Page) =>
    page.getByRole("button", { name: /Register/i }).click(),
};

export const logoutQueries = {
  getLogoutButton: (page: Page) =>
    page.getByRole("button", { name: /logout/i }),
};

export const register = async (
  page: Page,
  username = `USERNAME-${crypto.randomUUID().toString()}`,
  password = `PASSWORD-${crypto.randomUUID().toString()}`,
) => {
  await page.goto("/registration");
  await registrationQueries.getUsernameInput(page).fill(username);
  await registrationQueries.getPasswordInput(page).fill(password);
  await registrationQueries.getConfirmPasswordInput(page).fill(password);
  await registrationQueries.clickRegisterButton(page);
  await expect(page).toHaveURL("/");
  return { username, password };
};

export const logout = async (page: Page) =>
  logoutQueries.getLogoutButton(page).click();
