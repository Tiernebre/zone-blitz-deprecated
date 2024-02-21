package com.tiernebre.game_simulation.dto;

import com.tiernebre.game_simulation.dto.personnel.DepthChart;

public record Team(
  String location,
  String mascot,
  Player[] players,
  DepthChart depthChart,
  CoachingStaff coachingStaff
) {}
