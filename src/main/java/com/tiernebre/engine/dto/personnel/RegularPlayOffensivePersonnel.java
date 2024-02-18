package com.tiernebre.engine.dto.personnel;

import com.tiernebre.engine.dto.Player;

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
