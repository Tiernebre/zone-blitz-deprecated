package com.tiernebre.game_simulation.game.play.regular.pass;

import com.tiernebre.game_simulation.GameSimulationCommonDependencies;
import com.tiernebre.game_simulation.game.play.regular.defense.RandomTackledByCalculator;
import com.tiernebre.game_simulation.game.play.regular.pass.completion.DefaultCompletedPassCalculator;
import com.tiernebre.game_simulation.game.play.regular.pass.interception.PercentageInterceptionCalculator;
import org.apache.commons.math3.distribution.NormalDistribution;

public class PassingSimulatorFactory {

  public PassingSimulator create(
    GameSimulationCommonDependencies dependencies
  ) {
    return new DefaultPassingSimulator(
      new DistributionPassingYardageCalculator(
        new NormalDistribution(11.43082, 10.13847)
      ),
      new DefaultCompletedPassCalculator(
        // TODO: implementation of a pass accuracy calculator
        null,
        // TODO: implementation of a pass completion calculator
        null
      ),
      new PercentageInterceptionCalculator(dependencies.percentageCalculator()),
      new RandomTackledByCalculator(dependencies.random())
    );
  }
}
