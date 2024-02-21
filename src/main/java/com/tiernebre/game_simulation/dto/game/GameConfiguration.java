package com.tiernebre.game_simulation.dto.game;

public record GameConfiguration(
  int totalSeconds,
  ScoringConfiguration scoring,
  DriveConfiguration drive
) {}
