import { Locator, expect as baseExpect } from "@playwright/test";

export const expect = baseExpect.extend({
  async toBeInvalid(locator: Locator) {
    const assertionName = "toBeInvalid";
    let pass: boolean;

    try {
      baseExpect(
        await locator.evaluate(
          (node) =>
            node.matches(":invalid") &&
            node instanceof HTMLInputElement &&
            node.validationMessage === "Please fill out this field.",
        ),
      ).toStrictEqual(true);
      pass = true;
    } catch (e: any) {
      pass = false;
    }

    return {
      name: assertionName,
      message: pass
        ? () => "Found node was invalid"
        : () => "Found node was valid",
      pass,
    };
  },
});
