package com.tiernebre.engine.play.regular;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.dto.personnel.RegularPlayOffensivePersonnel;

public record RegularPlayOffensiveDecision(
  RegularPlayOffensivePersonnel personnel,
  Player target
) {}
