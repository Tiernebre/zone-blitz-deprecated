package com.tiernebre.engine.play.regular.turnover;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.engine.util.PercentageCalculator;

public class PercentageFumbleCalculator implements FumbleCalculator {

  private final double FUMBLE_RATE_PERCENTAGE = 0.01753327;
  private final PercentageCalculator percentageCalculator;

  public PercentageFumbleCalculator(PercentageCalculator percentageCalculator) {
    this.percentageCalculator = percentageCalculator;
  }

  @Override
  public boolean occurred(
    Player carrier,
    RegularPlayDefensivePersonnel defense
  ) {
    return percentageCalculator.calculate(FUMBLE_RATE_PERCENTAGE);
  }
}
