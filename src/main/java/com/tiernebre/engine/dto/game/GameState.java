package com.tiernebre.engine.dto.game;

public record GameState(
  Drive drive,
  GameScoreboard score,
  GameTime time,
  CoinTossDecision coinTossDecision,
  Side onOffense,
  boolean isKickoff,
  boolean isExtraPointAttempt
) {}
