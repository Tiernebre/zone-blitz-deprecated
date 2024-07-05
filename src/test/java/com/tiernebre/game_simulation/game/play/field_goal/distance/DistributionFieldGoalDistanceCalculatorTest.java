package com.tiernebre.game_simulation.game.play.field_goal.distance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.dto.DtoMockFactory;
import com.tiernebre.game_simulation.game.play.field_goal.FieldGoalConstants;
import com.tiernebre.game_simulation.game.play.field_goal.distance.DistributionFieldGoalDistanceCalculator;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.junit.jupiter.api.Test;

public class DistributionFieldGoalDistanceCalculatorTest {

  private final JDKRandomGenerator randomGenerator = new JDKRandomGenerator(0);
  private final NormalDistribution distribution = new NormalDistribution(
    randomGenerator,
    FieldGoalConstants.DISTANCE_MEAN,
    FieldGoalConstants.DISTANCE_STANDARD_DEVIATION
  );
  private final DistributionFieldGoalDistanceCalculator distanceCalculator =
    new DistributionFieldGoalDistanceCalculator(distribution);
  private final int SAMPLED_YARDS = 45;

  @Test
  public void returnsAverageDistance() {
    var kicker = DtoMockFactory.player(
      EngineConstants.AVERAGE_PLAYER_ATTRIBUTE
    );
    assertEquals(SAMPLED_YARDS, distanceCalculator.calculate(kicker));
  }

  @Test
  public void returnsMinimumDistance() {
    var kicker = DtoMockFactory.player(
      EngineConstants.MINIMUM_PLAYER_ATTRIBUTE
    );
    assertEquals(
      (int) (SAMPLED_YARDS - (int) distribution.getStandardDeviation()),
      distanceCalculator.calculate(kicker)
    );
  }

  @Test
  public void returnsMaxDistance() {
    var kicker = DtoMockFactory.player(
      EngineConstants.MAXIMUM_PLAYER_ATTRIBUTE
    );
    assertEquals(
      (int) (SAMPLED_YARDS + (int) distribution.getStandardDeviation()),
      distanceCalculator.calculate(kicker)
    );
  }
}
