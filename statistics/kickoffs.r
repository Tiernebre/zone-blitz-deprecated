library(tidyverse)
library(nflreadr)

data <- load_pbp(1999:2022)

kickoffs <- data %>%
  filter(kickoff_attempt == 1)

successful_kickoffs <- kickoffs %>%
  filter(kick_distance != "NA")

kickoff_returns <- successful_kickoffs %>%
  filter(touchback == 0)

kickoff_yards <- successful_kickoffs$kick_distance
kickoff_return_yards <- kickoff_returns$yards_gained

mean(kickoff_yards) %>% print()
sd(kickoff_yards) %>% print()

mean(kickoff_return_yards) %>% print()
sd(kickoff_return_yards) %>% print()
