package com.tiernebre.game_simulation.dto.game;

public record GameState(
  Drive drive,
  GameScoreboard score,
  GameTime time,
  CoinTossDecision coinTossDecision,
  Side onOffense,
  boolean isKickoff,
  boolean isExtraPointAttempt
) {}
