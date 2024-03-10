import { test, expect } from '@playwright/test'

test('GET /api/health', async ({ request }) => {
  expect(await request.get("/api/health")).toBeOK();
})
