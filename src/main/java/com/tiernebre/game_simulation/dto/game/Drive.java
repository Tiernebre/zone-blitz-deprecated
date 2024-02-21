package com.tiernebre.game_simulation.dto.game;

public record Drive(
  Down down,
  int lineOfScrimmage,
  int lineToGain,
  Direction direction
) {}
