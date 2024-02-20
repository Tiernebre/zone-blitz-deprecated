package com.tiernebre.game_simulation.dto;

public record PlayerAttributes(
  int runBlocking,
  int rushingAbility,
  int tackling,
  int blockShedding,
  int kickPower,
  int kickAccuracy
) {}
