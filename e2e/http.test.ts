import { test, expect } from "@playwright/test";

test("sets content security policy", async ({ request }) => {
  const httpResponse = await request.get("/api/health");
  expect(httpResponse.headers()["content-security-policy"]).toBeTruthy();
  expect(httpResponse.headers()["content-security-policy"]).toStrictEqual(
    `default-src https://dev.zoneblitz.app`,
  );
});

test("sets content security policy for login page with google", async ({
  request,
}) => {
  const httpResponse = await request.get("/login");
  expect(httpResponse.headers()["content-security-policy"]).toBeTruthy();
  expect(httpResponse.headers()["content-security-policy"]).toStrictEqual(
    `default-src https://dev.zoneblitz.app https://accounts.google.com; style-src 'self' https://accounts.google.com 'unsafe-inline'`,
  );
});
