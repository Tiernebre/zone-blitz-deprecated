package com.tiernebre.game_simulation.play.punt;

public interface PuntSimulator {
  public PuntResult simulate(
    PuntOffensivePlaycall offensivePlaycall,
    PuntDefensivePlayCall defensivePlayCall
  );
}
