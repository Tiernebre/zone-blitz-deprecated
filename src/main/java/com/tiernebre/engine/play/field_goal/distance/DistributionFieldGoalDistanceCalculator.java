package com.tiernebre.engine.play.field_goal.distance;

import com.tiernebre.engine.EngineConstants;
import com.tiernebre.engine.dto.Player;
import org.apache.commons.math3.distribution.NormalDistribution;

public class DistributionFieldGoalDistanceCalculator
  implements FieldGoalDistanceCalculator {

  private final NormalDistribution distribution;

  public DistributionFieldGoalDistanceCalculator(
    NormalDistribution distribution
  ) {
    this.distribution = distribution;
  }

  @Override
  public int calculate(Player kicker) {
    var factor =
      (kicker.attributes().kickPower() -
        EngineConstants.AVERAGE_PLAYER_ATTRIBUTE) /
      EngineConstants.AVERAGE_PLAYER_ATTRIBUTE;
    return (int) (distribution.sample() +
      distribution.getStandardDeviation() * factor);
  }
}
