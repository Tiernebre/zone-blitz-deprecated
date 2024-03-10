import { defineConfig, devices } from '@playwright/test';

export default defineConfig({
  testDir: 'e2e',
  use: {
    baseURL: 'https://dev.zoneblitz.app',
    headless: true,
    channel: "/usr/bin/chromium-browser",
    launchOptions: {
      executablePath: "/usr/bin/chromium-browser"
    }
  }
});
