package com.tiernebre.game_simulation.dto;

import com.tiernebre.game_simulation.playbook.defense.DefensivePlaybook;
import com.tiernebre.game_simulation.playbook.offense.OffensivePlaybook;

public record Coach(
  String firstName,
  String lastName,
  CoachingSpecialty specialty,
  OffensivePlaybook offensivePlaybook,
  DefensivePlaybook defensivePlaybook
) {}
