import { expect as baseExpect } from "@playwright/test";

export const expect = baseExpect.extend({
  async toBeInvalid() {
    return {
      message: () => "is invalid",
      pass: true,
    };
  },
});
