package com.tiernebre.game_simulation.game.play.kickoff;

import com.tiernebre.game_simulation.dto.Player;
import org.apache.commons.math3.distribution.NormalDistribution;

public final class DistributionKickoffDistanceCalculator
  implements KickoffDistanceCalculator {

  private final NormalDistribution distribution;

  public DistributionKickoffDistanceCalculator(
    NormalDistribution distribution
  ) {
    this.distribution = distribution;
  }

  @Override
  public int calculate(Player kicker) {
    return (int) distribution.sample();
  }
}
