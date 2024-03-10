import { defineConfig } from '@playwright/test';

export default defineConfig({
  testDir: 'e2e',
  use: {
    baseURL: 'http://0.0.0.0:8000',
    headless: true,
    channel: "/usr/bin/chromium-browser",
    launchOptions: {
      executablePath: "/usr/bin/chromium-browser"
    }
  },
  webServer: {
    command: 'make',
    url: 'http://0.0.0.0:8000',
  }
});
