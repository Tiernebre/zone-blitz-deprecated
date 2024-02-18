package com.tiernebre.engine.dto.personnel;

import com.tiernebre.engine.dto.Player;

public record RegularPlayDefensivePersonnel(
  Player[] linemen,
  Player[] linebackers,
  Player[] cornerbacks,
  Player freeSafety,
  Player strongSafety
) {}
