package com.tiernebre.game_simulation.dto.personnel;

import com.tiernebre.game_simulation.dto.Player;

public record RegularPlayOffensivePersonnel(
  Player quarterback,
  Player[] runningBacks,
  Player[] tightEnds,
  Player[] wideReceivers,
  Player leftTackle,
  Player leftGuard,
  Player center,
  Player rightGuard,
  Player rightTackle
) {}
