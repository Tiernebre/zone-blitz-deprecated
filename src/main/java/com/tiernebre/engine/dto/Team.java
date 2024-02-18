package com.tiernebre.engine.dto;

import com.tiernebre.engine.dto.personnel.DepthChart;

public record Team(
  String location,
  String mascot,
  Player[] players,
  DepthChart depthChart,
  CoachingStaff coachingStaff
) {}
