import { BrowserContext, Page } from "playwright";
import { expect } from "playwright/test";
import { headerQueries } from "./header";

export const getSessionCookie = async (context: BrowserContext) =>
  (await context.cookies()).find((cookie) => cookie.name === "zb_session_id");

export const sessionCookieExists = async (context: BrowserContext) =>
  !!(await getSessionCookie(context));

export const assertLoggedIn = async (page: Page, context: BrowserContext) => {
  await expect(headerQueries.getLogoutButton(page)).toBeVisible();
  await expect(headerQueries.getLoginButton(page)).not.toBeVisible();
  await expect(headerQueries.getRegisterButton(page)).not.toBeVisible();
  const sessionCookie = await getSessionCookie(context);
  expect(sessionCookie).toBeTruthy();
  expect(sessionCookie!.httpOnly).toBeTruthy();
  expect(sessionCookie!.sameSite).toStrictEqual("Strict");
  expect(sessionCookie!.domain).toStrictEqual("dev.zoneblitz.app");
  expect(sessionCookie!.secure).toBeTruthy();
  expect(sessionCookie!.value).toBeTruthy();
  expect(sessionCookie!.expires).toBeTruthy();
};

export const assertLoggedOut = async (page: Page, context: BrowserContext) => {
  await expect(headerQueries.getLogoutButton(page)).not.toBeVisible();
  await expect(headerQueries.getLoginButton(page)).toBeVisible();
  await expect(headerQueries.getRegisterButton(page)).toBeVisible();
  expect(await sessionCookieExists(context)).toStrictEqual(false);
};
