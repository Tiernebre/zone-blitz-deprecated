package com.tiernebre.engine.dto.personnel;

import com.tiernebre.engine.dto.Player;

public record DefensiveDepthChart(
  Player[] willEdges,
  Player[] defensiveTackles,
  Player[] samEdges,
  Player[] willLinebackers,
  Player[] mikeLinebackers,
  Player[] samLinebackers,
  Player[] willCornerbacks,
  Player[] samCornerbacks,
  Player[] nickelbacks,
  Player[] strongSafeties,
  Player[] freeSafeties
) {}
