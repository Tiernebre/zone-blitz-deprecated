package com.tiernebre.game_simulation.util;

import java.util.Random;

public final class PercentageCalculator {

  private final Random random;

  public PercentageCalculator(Random random) {
    this.random = random;
  }

  /**
   * Given a double that is 0.0 <= 1.0, this function will calculate the percentage that
   * this occurs out of a 100 percent range.
   *
   * i.e. given `percentage` = `0.55`, this will return true 55% of the time.
   *
   * @param percentage A double that is between 0.0 and 1.0 that represents a percentage
   * @return true if the percentage occurred, false otherwise.
   */
  public boolean calculate(double percentage) {
    return random.nextDouble() <= percentage;
  }
}
