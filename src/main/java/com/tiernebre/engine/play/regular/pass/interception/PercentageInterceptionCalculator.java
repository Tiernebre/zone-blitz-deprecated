package com.tiernebre.engine.play.regular.pass.interception;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.util.PercentageCalculator;

public final class PercentageInterceptionCalculator
  implements InterceptionCalculator {

  private static final double INTERCEPTION_PERCENTAGE = 0.0239576;
  private final PercentageCalculator percentageCalculator;

  public PercentageInterceptionCalculator(
    PercentageCalculator percentageCalculator
  ) {
    this.percentageCalculator = percentageCalculator;
  }

  @Override
  public boolean intercepted(Player thrower) {
    return percentageCalculator.calculate(INTERCEPTION_PERCENTAGE);
  }
}
