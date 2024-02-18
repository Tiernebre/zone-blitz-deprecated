package com.tiernebre.engine.dto.personnel;

public record DepthChart(
  OffensiveDepthChart offense,
  DefensiveDepthChart defense
) {}
