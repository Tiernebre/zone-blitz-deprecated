package com.tiernebre.engine.dto.personnel;

import com.tiernebre.engine.dto.Player;

public record FieldGoalKickingPersonnel(
  Player kicker,
  Player holder,
  Player longSnapper,
  Player[] blockers
) {}
