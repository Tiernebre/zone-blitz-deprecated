import { PlaywrightTestConfig, defineConfig, devices } from "@playwright/test";

const URL = process.env.ZONE_BLITZ_URL;
const DEBUG = process.env.DEBUG;

const LOCAL_CONFIG: PlaywrightTestConfig = {
  use: {
    headless: true,
    channel: "/usr/bin/chromium-browser",
    launchOptions: {
      executablePath: "/usr/bin/chromium-browser",
      args: ["--allow-insecure-localhost"],
    },
    ignoreHTTPSErrors: true,
  },
  webServer: {
    command: `JAVA_TOOL_OPTIONS="-Dorg.slf4j.simpleLogger.defaultLogLevel=${DEBUG ? "debug" : "info"} -Dorg.gradle.native=false -Dorg.jooq.no-logo=true -Dorg.jooq.no-tips=true" make${DEBUG ? " debug" : ""}`,
    url: URL,
    ignoreHTTPSErrors: true,
    timeout: DEBUG ? 99999999 : undefined,
  },
  fullyParallel: false,
  timeout: DEBUG ? 99999999 : undefined,
};

const CI_CONFIG: PlaywrightTestConfig = {
  use: {
    // TODO: hacky fix to make self signed cert work in CI, look into how Playwright uses trusted certs.
    ignoreHTTPSErrors: true,
  },
  projects: [
    {
      name: "chromium",
      use: { ...devices["Desktop Chrome"] },
    },
    {
      name: "firefox",
      use: { ...devices["Desktop Firefox"] },
    },
    {
      name: "webkit",
      use: { ...devices["Desktop Safari"] },
    },
  ],
};

const additionalConfiguration: PlaywrightTestConfig = process.env.CI
  ? CI_CONFIG
  : LOCAL_CONFIG;

export default defineConfig({
  ...additionalConfiguration,
  testDir: "e2e",
  use: {
    baseURL: URL,
    ...additionalConfiguration.use,
  },
});
