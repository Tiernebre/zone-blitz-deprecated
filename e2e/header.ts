import { Page } from "playwright";

export const headerQueries = {
  getLoginButton: (page: Page) =>
    page.locator("header").getByRole("link", { name: /login/i }),
  getRegisterButton: (page: Page) =>
    page.locator("header").getByRole("link", { name: /register/i }),
  getLogoutButton: (page: Page) =>
    page.locator("header").getByRole("button", { name: /logout/i }),
};
