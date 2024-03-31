import AxeBuilder from "@axe-core/playwright";
import {
  BrowserContext,
  Locator,
  Page,
  expect as baseExpect,
} from "@playwright/test";
import { assertLoggedIn, assertLoggedOut } from "./session";

export const VALIDATION_MESSAGES = Object.freeze({
  REQUIRED: /fill out this field/i,
  PATTERN: /match the requested format/i,
  MINLENGTH: (length: number) => new RegExp(`${length} characters`, "i"),
});

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
      await assertLoggedIn(page, context);
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
      await assertLoggedOut(page, context);
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
  async toBeAccessible(page: Page) {
    let pass;
    try {
      const { violations } = await new AxeBuilder({ page }).analyze();
      expect(violations).toHaveLength(0);
      pass = true;
    } catch (error) {
      pass = false;
    }
    return {
      name: "toBeAccessible",
      pass,
      message: pass
        ? () => "Page was accessible"
        : () => "Page was not accessible",
    };
  },
});
