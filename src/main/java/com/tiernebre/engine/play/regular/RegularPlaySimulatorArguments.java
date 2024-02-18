package com.tiernebre.engine.play.regular;

import com.tiernebre.engine.dto.personnel.RegularPlayDefensivePersonnel;

public record RegularPlaySimulatorArguments(
  RegularPlayOffensiveDecision offensiveDecision,
  RegularPlayDefensivePersonnel defensivePersonnel
) {}
