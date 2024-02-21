package com.tiernebre.game_simulation.play.kickoff;

public interface KickoffSimulator {
  public KickoffResult simulate(
    KickReturnPlayCall kickReturnPlayCall,
    KickoffPlayCall kickoffPlayCall
  );
}
