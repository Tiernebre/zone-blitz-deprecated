package com.tiernebre.game_simulation.dto.personnel;

import com.tiernebre.game_simulation.dto.Player;

public record RegularPlayDefensivePersonnel(
  Player[] linemen,
  Player[] linebackers,
  Player[] cornerbacks,
  Player freeSafety,
  Player strongSafety
) {}
