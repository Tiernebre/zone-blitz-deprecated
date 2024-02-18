package com.tiernebre.engine.dto.game;

public record GameConfiguration(
  int totalSeconds,
  ScoringConfiguration scoring,
  DriveConfiguration drive
) {}
