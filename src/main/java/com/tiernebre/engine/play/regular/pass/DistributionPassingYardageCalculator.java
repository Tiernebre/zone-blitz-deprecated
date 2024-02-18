package com.tiernebre.engine.play.regular.pass;

import com.tiernebre.engine.play.regular.RegularPlaySimulatorArguments;
import org.apache.commons.math3.distribution.NormalDistribution;

public final class DistributionPassingYardageCalculator
  implements PassingYardageCalculator {

  private final NormalDistribution distribution;

  public DistributionPassingYardageCalculator(NormalDistribution distribution) {
    this.distribution = distribution;
  }

  @Override
  public int calculate(RegularPlaySimulatorArguments arguments) {
    return (int) distribution.sample();
  }
}
