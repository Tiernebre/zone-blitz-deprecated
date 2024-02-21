package com.tiernebre.game_simulation.dto.game;

public record ScoringConfiguration(
  int touchdownPoints,
  int fieldGoalPoints,
  int extraPointAttemptPoints,
  int twoPointConversionAttemptPoints,
  int safetyPoints
) {}
