package com.tiernebre.game_simulation.play.regular;

import com.tiernebre.game_simulation.dto.personnel.RegularPlayDefensivePersonnel;

public record RegularPlaySimulatorArguments(
  RegularPlayOffensiveDecision offensiveDecision,
  RegularPlayDefensivePersonnel defensivePersonnel
) {}
