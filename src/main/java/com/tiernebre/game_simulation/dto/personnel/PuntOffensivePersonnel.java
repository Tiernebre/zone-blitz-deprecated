package com.tiernebre.game_simulation.dto.personnel;

import com.tiernebre.game_simulation.dto.Player;

public record PuntOffensivePersonnel(
  Player punter,
  Player longSnapper,
  Player[] blockers,
  Player[] gunners
) {}
