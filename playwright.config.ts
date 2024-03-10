import { PlaywrightTestConfig, defineConfig, devices } from '@playwright/test';

const URL = process.env.ZONE_BLITZ_URL || 'http://0.0.0.0:8000'

const LOCAL_CONFIG: PlaywrightTestConfig = {
  use: {
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
}

const CI_CONFIG: PlaywrightTestConfig = {
  projects: [
    {
      name: 'chromium',
      use: { ...devices['Desktop Chrome'] },
    },

    {
      name: 'firefox',
      use: { ...devices['Desktop Firefox'] },
    },

    {
      name: 'webkit',
      use: { ...devices['Desktop Safari'] },
    },
  ]
}

const additionalConfiguration: PlaywrightTestConfig = process.env.CI ? CI_CONFIG : LOCAL_CONFIG;

export default defineConfig({
  ...additionalConfiguration,
  testDir: 'e2e',
  use: {
    baseURL: URL,
    ...additionalConfiguration.use,
  },
});
