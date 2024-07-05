package com.tiernebre.game_simulation.game.play.field_goal;

import com.tiernebre.game_simulation.GameSimulationCommonDependencies;
import com.tiernebre.game_simulation.game.play.field_goal.blocked.PercentageFieldGoalBlockCalculator;

public final class FieldGoalSimulatorFactory {

  public FieldGoalSimulator create(
    GameSimulationCommonDependencies dependencies
  ) {
    // TODO: complete injecting dependencies
    return new DefaultFieldGoalSimulator(
      new PercentageFieldGoalBlockCalculator(
        dependencies.percentageCalculator()
      ),
      null,
      null,
      null
    );
  }
}
