library(tidyverse)
library(nflreadr)

data <- load_pbp(1999:2022)

plays <- data %>%
  filter(rush == 1) %>%
  select(fumble)

number_of_plays <- plays %>%
  nrow()

number_of_fumbles <- plays %>%
  filter(fumble == 1) %>%
  nrow()

fumble_rate <- number_of_fumbles / number_of_plays

print(fumble_rate)
