library(tidyverse)
library(nflreadr)

data <- load_pbp(1999:2022)

rushing_plays <- data %>%
  filter(rush == 1 & rushing_yards != "NA") %>%
  select(rushing_yards)

rushing_yards <- rushing_plays$rushing_yards

mean(rushing_yards) %>%
  print()

sd(rushing_yards) %>%
  print()

passing_plays <- data %>%
  filter(pass == 1 & complete_pass == 1 & passing_yards != "NA") %>%
  select(passing_yards)

passing_yards <- passing_plays$passing_yards

mean(passing_yards) %>%
  print()

sd(passing_yards) %>%
  print()
