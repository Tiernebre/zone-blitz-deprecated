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
    var kicker = kickoffPlayCall.personnel().kickOffSpecialist();
    return new KickoffReturnResult(
      kicker,
      distanceCalculator.calculate(kicker)
    );
  }
}
