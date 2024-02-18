package com.tiernebre.engine.play.punt;

import com.tiernebre.engine.dto.Player;
import org.apache.commons.math3.distribution.NormalDistribution;

public final class DistributionPuntDistanceCalculator
  implements PuntDistanceCalculator {

  private final NormalDistribution distribution;

  public DistributionPuntDistanceCalculator(NormalDistribution distribution) {
    this.distribution = distribution;
  }

  @Override
  public int distance(Player punter) {
    return (int) distribution.sample();
  }
}
