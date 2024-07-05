package com.tiernebre.game_simulation.game.play.regular;

import com.tiernebre.game_simulation.personnel.defense.RegularPlayDefensivePersonnel;

public record RegularPlaySimulatorArguments(
  RegularPlayOffensiveDecision offensiveDecision,
  RegularPlayDefensivePersonnel defensivePersonnel
) {}
