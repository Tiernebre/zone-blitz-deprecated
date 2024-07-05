package com.tiernebre.game_simulation.play.regular.rush;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.game.play.regular.rush.DistributionRushingYardageCalculator;
import com.tiernebre.game_simulation.play.regular.RegularPlaySimulatorMockFactory;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.junit.jupiter.api.Test;

public class DistributionRushingYardageCalculatorTest {

  private static final int EXPECTED_SEEDED_YARDS = 7;

  @Test
  public void returnsBalancedYardage() {
    assertEquals(
      EXPECTED_SEEDED_YARDS,
      calculator().calculate(RegularPlaySimulatorMockFactory.arguments())
    );
  }

  @Test
  public void returnsDefensivelyFavoredRushingResult() {
    assertTrue(
      calculator()
        .calculate(
          RegularPlaySimulatorMockFactory.arguments(
            EngineConstants.AVERAGE_PLAYER_ATTRIBUTE,
            EngineConstants.MAXIMUM_PLAYER_ATTRIBUTE
          )
        ) <
      EXPECTED_SEEDED_YARDS
    );
  }

  @Test
  public void returnsOffensivelyFavoredRushingResult() {
    assertTrue(
      calculator()
        .calculate(
          RegularPlaySimulatorMockFactory.arguments(
            EngineConstants.MAXIMUM_PLAYER_ATTRIBUTE,
            EngineConstants.AVERAGE_PLAYER_ATTRIBUTE
          )
        ) >
      EXPECTED_SEEDED_YARDS
    );
  }

  private DistributionRushingYardageCalculator calculator() {
    return new DistributionRushingYardageCalculator(
      new NormalDistribution(new JDKRandomGenerator(0), 3.0, 5.0)
    );
  }
}
