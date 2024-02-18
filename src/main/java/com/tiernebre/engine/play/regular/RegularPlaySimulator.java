package com.tiernebre.engine.play.regular;

public interface RegularPlaySimulator {
  public RegularPlayResult simulate(
    RegularPlayOffensivePlayCall offensivePlayCall,
    RegularPlayDefensivePlayCall defensivePlayCall
  );
}
