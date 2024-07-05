package com.tiernebre.game_simulation.game.play.kickoff;

public final class DefaultKickoffSimulator implements KickoffSimulator {

  private final KickoffDistanceCalculator distanceCalculator;

  public DefaultKickoffSimulator(KickoffDistanceCalculator distanceCalculator) {
    this.distanceCalculator = distanceCalculator;
  }

  @Override
  public KickoffResult simulate(
    KickReturnPlayCall kickReturnPlayCall,
    KickoffPlayCall kickoffPlayCall
  ) {
    return new KickoffReturnResult(
      kickoffPlayCall.personnel().kickOffSpecialist(),
      0
    );
  }
}
