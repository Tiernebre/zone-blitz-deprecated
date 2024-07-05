package com.tiernebre.game_simulation.game.play.field_goal.accuracy;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.game.play.field_goal.FieldGoalConstants;
import com.tiernebre.game_simulation.util.PercentageCalculator;

public class PercentageFieldGoalAccuracyCalculator
  implements FieldGoalAccuracyCalculator {

  private final PercentageCalculator percentageCalculator;

  public PercentageFieldGoalAccuracyCalculator(
    PercentageCalculator percentageCalculator
  ) {
    this.percentageCalculator = percentageCalculator;
  }

  @Override
  public boolean made(Player kicker) {
    return percentageCalculator.calculate(
      FieldGoalConstants.ACCURATE_PERCENTAGE
    );
  }
}
