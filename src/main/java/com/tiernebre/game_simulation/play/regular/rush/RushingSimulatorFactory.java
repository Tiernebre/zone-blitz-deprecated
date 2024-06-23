package com.tiernebre.game_simulation.play.regular.rush;

import com.tiernebre.game_simulation.GameSimulationCommonDependencies;
import com.tiernebre.game_simulation.play.regular.turnover.PercentageFumbleCalculator;
import org.apache.commons.math3.distribution.NormalDistribution;

public final class RushingSimulatorFactory {

  public RushingSimulator create(
    GameSimulationCommonDependencies dependencies
  ) {
    return new DefaultRushingSimulator(
      new DistributionRushingYardageCalculator(
        new NormalDistribution(4.174688, 6.283547)
      ),
      new PercentageFumbleCalculator(dependencies.percentageCalculator()),
      dependencies.tackledByCalculator()
    );
  }
}
