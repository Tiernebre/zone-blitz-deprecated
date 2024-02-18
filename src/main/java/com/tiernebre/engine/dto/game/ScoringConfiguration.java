package com.tiernebre.engine.dto.game;

public record ScoringConfiguration(
  int touchdownPoints,
  int fieldGoalPoints,
  int extraPointAttemptPoints,
  int twoPointConversionAttemptPoints,
  int safetyPoints
) {}
