package com.tiernebre.game_simulation.play.regular.pass;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tiernebre.game_simulation.game.play.regular.pass.DistributionPassingYardageCalculator;
import com.tiernebre.game_simulation.play.regular.RegularPlaySimulatorMockFactory;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.junit.jupiter.api.Test;

public class DistributionPassingYardageCalculatorTest {

  private static final int EXPECTED_SEEDED_YARDS = 7;

  @Test
  public void returnsBalancedYardage() {
    assertEquals(
      EXPECTED_SEEDED_YARDS,
      calculator().calculate(RegularPlaySimulatorMockFactory.arguments())
    );
  }

  private DistributionPassingYardageCalculator calculator() {
    return new DistributionPassingYardageCalculator(
      new NormalDistribution(new JDKRandomGenerator(0), 3.0, 5.0)
    );
  }
}
