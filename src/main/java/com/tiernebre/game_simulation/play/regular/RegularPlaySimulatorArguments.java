package com.tiernebre.game_simulation.play.regular;

import com.tiernebre.game_simulation.playbook.defense.RegularPlayDefensivePersonnel;

public record RegularPlaySimulatorArguments(
  RegularPlayOffensiveDecision offensiveDecision,
  RegularPlayDefensivePersonnel defensivePersonnel
) {}
