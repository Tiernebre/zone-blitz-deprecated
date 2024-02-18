package com.tiernebre.engine.play.kickoff;

public interface KickoffSimulator {
  public KickoffResult simulate(
    KickReturnPlayCall kickReturnPlayCall,
    KickoffPlayCall kickoffPlayCall
  );
}
