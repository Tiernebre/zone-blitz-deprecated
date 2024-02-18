package com.tiernebre.engine.play.punt;

public interface PuntSimulator {
  public PuntResult simulate(
    PuntOffensivePlaycall offensivePlaycall,
    PuntDefensivePlayCall defensivePlayCall
  );
}
