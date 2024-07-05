package com.tiernebre.game_simulation.game.play.kickoff;

import com.tiernebre.game_simulation.GameSimulationCommonDependencies;
import org.apache.commons.math3.distribution.NormalDistribution;

public final class KickoffSimulatorFactory {

  public KickoffSimulator create(
    GameSimulationCommonDependencies dependencies
  ) {
    return new DefaultKickoffSimulator(
      new DistributionKickoffDistanceCalculator(
        new NormalDistribution(62.44247, 10.49669)
      ),
      new DistributionKickoffReturnAttemptYardageCalculator(null),
      dependencies.tackledByCalculator()
    );
  }
}
