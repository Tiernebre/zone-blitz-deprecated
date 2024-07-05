package com.tiernebre.game_simulation.game.play.kickoff;

public interface KickoffSimulator {
  public KickoffResult simulate(
    KickReturnPlayCall kickReturnPlayCall,
    KickoffPlayCall kickoffPlayCall
  );
}
