package com.tiernebre.game_simulation.play;

import com.tiernebre.game_simulation.play.call.DefensivePlayCall;
import com.tiernebre.game_simulation.play.call.OffensivePlayCall;

public record PlaySimulatorArguments(
  OffensivePlayCall offensivePlayCall,
  DefensivePlayCall defensivePlayCall
) {}
