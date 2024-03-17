import { Page } from "playwright";

export const getHeaderLoginButton = (page: Page) =>
  page.locator("header").getByRole("button", { name: /login/i });
export const getHeaderRegisterButton = (page: Page) =>
  page.locator("header").getByRole("button", { name: /register/i });
export const getHeaderLogoutButton = (page: Page) =>
  page.locator("header").getByRole("button", { name: /logout/i });
