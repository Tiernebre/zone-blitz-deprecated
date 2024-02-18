library(tidyverse)
library(nflreadr)

data <- load_pbp(1999:2022)

pass_plays <- data %>%
  filter(pass == 1)

number_of_plays <- pass_plays %>%
  nrow()

number_of_interceptions <- pass_plays %>%
  filter(interception == 1) %>%
  nrow()

interception_rate <- number_of_interceptions / number_of_plays

print(interception_rate)
