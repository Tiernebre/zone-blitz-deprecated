library(tidyverse)
library(nflreadr)

data <- load_pbp(1999:2022)

kickoffs <- data %>%
  filter(kickoff_attempt == 1)

successful_kickoffs <- kickoffs %>%
  filter(kick_distance != "NA")

kickoff_yards <- successful_kickoffs$kick_distance

mean(kickoff_yards) %>% print()
sd(kickoff_yards) %>% print()
