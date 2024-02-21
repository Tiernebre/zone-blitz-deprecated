package com.tiernebre.game_simulation.dto.personnel;

import com.tiernebre.game_simulation.dto.Player;

public record FieldGoalKickingPersonnel(
  Player kicker,
  Player holder,
  Player longSnapper,
  Player[] blockers
) {}
