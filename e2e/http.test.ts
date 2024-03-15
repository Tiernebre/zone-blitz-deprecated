import { test, expect } from "@playwright/test";

test("sets content security policy", async ({ request }) => {
  const httpResponse = await request.get("/api/health");
  console.log(JSON.stringify(httpResponse.headers()));
  expect(httpResponse.headers()["content-security-policy"]).toBeTruthy();
  expect(httpResponse.headers()["content-security-policy"]).toStrictEqual(
    `default-src 'self' https://accounts.google.com/gsi/client`,
  );
});
