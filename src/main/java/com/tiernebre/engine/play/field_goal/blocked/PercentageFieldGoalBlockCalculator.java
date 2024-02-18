package com.tiernebre.engine.play.field_goal.blocked;

import com.tiernebre.engine.dto.personnel.FieldGoalBlockPersonnel;
import com.tiernebre.engine.dto.personnel.FieldGoalKickingPersonnel;
import com.tiernebre.engine.util.PercentageCalculator;

public final class PercentageFieldGoalBlockCalculator
  implements FieldGoalBlockCalculator {

  private final double PERCENTAGE = 0.02179445;

  private final PercentageCalculator percentageCalculator;

  public PercentageFieldGoalBlockCalculator(
    PercentageCalculator percentageCalculator
  ) {
    this.percentageCalculator = percentageCalculator;
  }

  @Override
  public boolean blocked(
    FieldGoalKickingPersonnel kickingPersonnel,
    FieldGoalBlockPersonnel blockPersonnel
  ) {
    return percentageCalculator.calculate(PERCENTAGE);
  }
}
