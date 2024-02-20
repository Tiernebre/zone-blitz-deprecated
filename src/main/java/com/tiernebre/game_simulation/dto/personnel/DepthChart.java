package com.tiernebre.game_simulation.dto.personnel;

public record DepthChart(
  OffensiveDepthChart offense,
  DefensiveDepthChart defense
) {}
