package com.tiernebre.game_simulation.play.regular;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.playbook.offense.personnel.RegularPlayOffensivePersonnel;

public record RegularPlayOffensiveDecision(
  RegularPlayOffensivePersonnel personnel,
  Player target
) {}
