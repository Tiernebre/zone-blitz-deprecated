package com.tiernebre.game_simulation.dto.personnel;

import com.tiernebre.game_simulation.dto.Player;

public record OffensiveLine(
  Player leftTackle,
  Player leftGuard,
  Player center,
  Player rightGuard,
  Player rightTackle
) {}
