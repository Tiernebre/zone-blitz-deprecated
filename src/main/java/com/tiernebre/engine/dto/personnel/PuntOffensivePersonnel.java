package com.tiernebre.engine.dto.personnel;

import com.tiernebre.engine.dto.Player;

public record PuntOffensivePersonnel(
  Player punter,
  Player longSnapper,
  Player[] blockers,
  Player[] gunners
) {}
