package com.tiernebre.engine.play.field_goal.accuracy;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.play.field_goal.FieldGoalConstants;
import com.tiernebre.engine.util.PercentageCalculator;

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
