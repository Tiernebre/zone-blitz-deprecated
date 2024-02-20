package com.tiernebre.game_simulation.play.regular.pass;

import static org.junit.Assert.assertEquals;

import com.tiernebre.game_simulation.play.regular.RegularPlaySimulatorMockFactory;
import com.tiernebre.game_simulation.play.regular.pass.DistributionPassingYardageCalculator;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.junit.Test;

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
