package com.tiernebre.engine.dto.game;

public record Drive(
  Down down,
  int lineOfScrimmage,
  int lineToGain,
  Direction direction
) {}
