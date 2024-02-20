package com.tiernebre.game_simulation.dto.game;

public record CoinTossResult(
  CoinToss toss,
  Side winner,
  CoinTossDecision decision,
  Direction homeInitialDirection,
  Direction awayInitialDirection
) {}
