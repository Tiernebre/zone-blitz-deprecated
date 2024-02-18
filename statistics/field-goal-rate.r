library(tidyverse)
library(nflreadr)

data <- load_pbp(1999:2022)

plays <- data %>%
  filter(field_goal_attempt == 1) %>%
  select(kick_distance, field_goal_result)

made_field_goals <- plays %>%
  filter(field_goal_result == "made")

missed_field_goals <- plays %>%
  filter(field_goal_result == "missed")

blocked_field_goals <- plays %>%
  filter(field_goal_result == "blocked")

number_of_made_field_goals <- nrow(made_field_goals)
number_of_missed_field_goals <- nrow(missed_field_goals)
number_of_blocked_field_goals <- nrow(blocked_field_goals)
total_field_goals <- nrow(plays)

accuracy_rate <- number_of_made_field_goals / total_field_goals
print(accuracy_rate)

blocked_rate <- number_of_blocked_field_goals / total_field_goals
print(blocked_rate)

kick_distances <- plays$kick_distance

mean(kick_distances) %>%
  print()

sd(kick_distances) %>%
  print()
