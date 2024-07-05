package com.tiernebre.game_simulation.game.play;

import com.tiernebre.game_simulation.game.play.call.DefensivePlayCall;
import com.tiernebre.game_simulation.game.play.call.OffensivePlayCall;

public record PlaySimulatorArguments(
  OffensivePlayCall offensivePlayCall,
  DefensivePlayCall defensivePlayCall
) {}
