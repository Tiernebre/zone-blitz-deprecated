package com.tiernebre.game_simulation.game.play.field_goal;

import com.tiernebre.game_simulation.GameSimulationCommonDependencies;
import com.tiernebre.game_simulation.game.play.field_goal.accuracy.PercentageFieldGoalAccuracyCalculator;
import com.tiernebre.game_simulation.game.play.field_goal.blocked.PercentageFieldGoalBlockCalculator;
import com.tiernebre.game_simulation.game.play.field_goal.blocked.RandomBlockedByCalculator;
import com.tiernebre.game_simulation.game.play.field_goal.distance.DistributionFieldGoalDistanceCalculator;

public final class FieldGoalSimulatorFactory {

  public FieldGoalSimulator create(
    GameSimulationCommonDependencies dependencies
  ) {
    return new DefaultFieldGoalSimulator(
      new PercentageFieldGoalBlockCalculator(
        dependencies.percentageCalculator()
      ),
      new DistributionFieldGoalDistanceCalculator(null),
      new PercentageFieldGoalAccuracyCalculator(
        dependencies.percentageCalculator()
      ),
      new RandomBlockedByCalculator(dependencies.random())
    );
  }
}
