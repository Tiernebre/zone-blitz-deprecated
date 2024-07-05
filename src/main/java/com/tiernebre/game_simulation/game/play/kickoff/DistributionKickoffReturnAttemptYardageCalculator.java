package com.tiernebre.game_simulation.game.play.kickoff;

import org.apache.commons.math3.distribution.NormalDistribution;

public final class DistributionKickoffReturnAttemptYardageCalculator
  implements KickoffReturnAttemptYardageCalculator {

  private final NormalDistribution distribution;

  public DistributionKickoffReturnAttemptYardageCalculator(
    NormalDistribution distribution
  ) {
    this.distribution = distribution;
  }

  @Override
  public int calculate() {
    return (int) distribution.sample();
  }
}
