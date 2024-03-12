import { Locator, expect as baseExpect } from "@playwright/test";

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
});
