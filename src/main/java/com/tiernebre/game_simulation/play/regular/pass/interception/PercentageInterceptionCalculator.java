package com.tiernebre.game_simulation.play.regular.pass.interception;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.util.PercentageCalculator;

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
