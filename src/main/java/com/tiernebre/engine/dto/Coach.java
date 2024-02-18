package com.tiernebre.engine.dto;

import com.tiernebre.engine.playbook.defense.DefensivePlaybook;
import com.tiernebre.engine.playbook.offense.OffensivePlaybook;

public record Coach(
  String firstName,
  String lastName,
  CoachingSpecialty specialty,
  OffensivePlaybook offensivePlaybook,
  DefensivePlaybook defensivePlaybook
) {}
