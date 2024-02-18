package com.tiernebre.engine.play;

import com.tiernebre.engine.play.call.DefensivePlayCall;
import com.tiernebre.engine.play.call.OffensivePlayCall;

public record PlaySimulatorArguments(
  OffensivePlayCall offensivePlayCall,
  DefensivePlayCall defensivePlayCall
) {}
