package com.tiernebre.game_simulation.dto.personnel;

import com.tiernebre.game_simulation.dto.Player;

public record OffensiveDepthChart(
  Player[] quarterbacks,
  Player[] runningBacks,
  Player[] tightEnds,
  Player[] wideReceivers,
  Player[] leftTackles,
  Player[] leftGuards,
  Player[] centers,
  Player[] rightGuards,
  Player[] rightTackles,
  Player[] kickers,
  Player[] punters,
  Player[] longSnappers
) {}
