library(tidyverse)
library(nflreadr)

data <- load_pbp(1999:2022)

punts <- data %>%
  filter(punt_attempt == 1)

successful_punts <- punts %>%
  filter(punt_blocked == 0 & kick_distance != "NA")

punt_yards <- successful_punts$kick_distance
punts_in_endzone <- successful_punts$punt_in_endzone
punts_inside_twenty <- successful_punts$punt_inside_twenty

punt_blocks <- punts %>%
  filter(punt_blocked == 1)

mean(punt_yards) %>% print()
sd(punt_yards) %>% print()
(nrow(punt_blocks) / nrow(punts)) %>% print()
