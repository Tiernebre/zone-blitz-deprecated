import { defineConfig } from '@playwright/test';

const URL = 'http://0.0.0.0:8000'

export default defineConfig({
  testDir: 'e2e',
  use: {
    baseURL: URL,
    headless: true,
    channel: "/usr/bin/chromium-browser",
    launchOptions: {
      executablePath: "/usr/bin/chromium-browser"
    }
  },
  webServer: {
    command: 'make',
    url: URL,
  }
});
