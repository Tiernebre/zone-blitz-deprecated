package com.tiernebre.game_simulation.play.punt;

import org.apache.commons.math3.distribution.NormalDistribution;

public final class PuntSimulatorFactory {

  public PuntSimulator create() {
    return new DefaultPuntSimulator(
      new DistributionPuntDistanceCalculator(
        new NormalDistribution(43.9941, 9.444654)
      )
    );
  }
}
