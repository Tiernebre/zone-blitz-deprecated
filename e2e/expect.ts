import {
  BrowserContext,
  Locator,
  Page,
  expect as baseExpect,
} from "@playwright/test";

export const VALIDATION_MESSAGES = Object.freeze({
  REQUIRED: /fill out this field/i,
  PATTERN: /match the requested format/i,
  MINLENGTH: (length: number) => new RegExp(`${length} characters`, "i"),
});

const getHeaderLoginButton = (page: Page) =>
  page.locator("header").getByRole("link", { name: /login/i });
const getHeaderRegisterButton = (page: Page) =>
  page.locator("header").getByRole("link", { name: /register/i });
const getHeaderLogoutButton = (page: Page) =>
  page.locator("header").getByRole("button", { name: /logout/i });

const getSessionCookie = async (context: BrowserContext) =>
  (await context.cookies()).find((cookie) => cookie.name === "zb_session_id");

export const expect = baseExpect.extend({
  async toBeValid(locator: Locator) {
    const assertionName = "toBeValid";
    let pass = true;
    let failingMessage: string;

    try {
      baseExpect(
        await locator.evaluate((node) => node instanceof HTMLInputElement),
      ).toStrictEqual(true);
    } catch (e: any) {
      return {
        name: assertionName,
        message: () =>
          "Element is not a valid form interactive element and cannot be valid or invalid.",
        pass: false,
      };
    }

    try {
      baseExpect(
        await locator.evaluate((node: HTMLInputElement) =>
          node.matches(":valid"),
        ),
      ).toStrictEqual(true);
    } catch (e: any) {
      pass = false;
      failingMessage = `Element is invalid, expected it to be valid`;
    }

    return {
      name: assertionName,
      message: pass ? () => "Element was valid." : () => failingMessage,
      pass,
    };
  },

  async toBeInvalid(locator: Locator, validationMessage: string | RegExp) {
    const assertionName = "toBeInvalid";
    let pass = true;
    let failingMessage: string;

    try {
      baseExpect(
        await locator.evaluate((node) => node instanceof HTMLInputElement),
      ).toStrictEqual(true);
    } catch (e: any) {
      return {
        name: assertionName,
        message: () =>
          "Element is not a valid form interactive element and cannot be valid or invalid.",
        pass,
      };
    }

    try {
      baseExpect(
        await locator.evaluate((node: HTMLInputElement) =>
          node.matches(":invalid"),
        ),
      ).toStrictEqual(true);
      baseExpect(
        await locator.evaluate(
          (node: HTMLInputElement) => node.validationMessage,
        ),
      ).toMatch(validationMessage);
    } catch (e: any) {
      pass = false;
      failingMessage = `Element is valid, expected it to be invalid with validation message ${validationMessage}`;
    }

    return {
      name: assertionName,
      message: pass ? () => "Element was invalid." : () => failingMessage,
      pass,
    };
  },
  async toBeLoggedIn({
    context,
    page,
  }: {
    context: BrowserContext;
    page: Page;
  }) {
    let pass;
    try {
      await expect(getHeaderLogoutButton(page)).toBeVisible();
      await expect(getHeaderLoginButton(page)).not.toBeVisible();
      await expect(getHeaderRegisterButton(page)).not.toBeVisible();
      const sessionCookie = await getSessionCookie(context);
      expect(sessionCookie).toBeTruthy();
      expect(sessionCookie!.httpOnly).toBeTruthy();
      expect(sessionCookie!.sameSite).toStrictEqual("Strict");
      expect(sessionCookie!.domain).toStrictEqual("dev.zoneblitz.app");
      expect(sessionCookie!.secure).toBeTruthy();
      expect(sessionCookie!.value).toBeTruthy();
      pass = true;
    } catch (error) {
      pass = false;
    }
    return {
      name: "toBeLoggedIn",
      pass,
      message: pass ? () => "User was logged in" : () => "User was logged out",
    };
  },
  async toBeLoggedOut({
    context,
    page,
  }: {
    context: BrowserContext;
    page: Page;
  }) {
    let pass;
    try {
      await expect(getHeaderLogoutButton(page)).not.toBeVisible();
      await expect(getHeaderLoginButton(page)).toBeVisible();
      await expect(getHeaderRegisterButton(page)).toBeVisible();
      const sessionCookie = await getSessionCookie(context);
      expect(sessionCookie).toBeFalsy();
      pass = true;
    } catch (error) {
      pass = false;
    }
    return {
      name: "toBeLoggedIn",
      pass,
      message: pass ? () => "User was logged out" : () => "User was logged in",
    };
  },
});
