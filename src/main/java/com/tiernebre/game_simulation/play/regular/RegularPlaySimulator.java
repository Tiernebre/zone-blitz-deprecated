package com.tiernebre.game_simulation.play.regular;

public interface RegularPlaySimulator {
  public RegularPlayResult simulate(
    RegularPlayOffensivePlayCall offensivePlayCall,
    RegularPlayDefensivePlayCall defensivePlayCall
  );
}
