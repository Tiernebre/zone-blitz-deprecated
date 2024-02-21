package com.tiernebre.game_simulation.play.field_goal;

import com.tiernebre.game_simulation.play.field_goal.accuracy.FieldGoalAccuracyCalculator;
import com.tiernebre.game_simulation.play.field_goal.blocked.BlockedByCalculator;
import com.tiernebre.game_simulation.play.field_goal.blocked.BlockedFieldGoalResult;
import com.tiernebre.game_simulation.play.field_goal.blocked.FieldGoalBlockCalculator;
import com.tiernebre.game_simulation.play.field_goal.distance.FieldGoalDistanceCalculator;
import com.tiernebre.game_simulation.play.field_goal.result.FieldGoalAttemptResult;
import com.tiernebre.game_simulation.play.field_goal.result.FieldGoalResult;

public final class DefaultFieldGoalSimulator implements FieldGoalSimulator {

  private final FieldGoalBlockCalculator blockCalculator;
  private final FieldGoalDistanceCalculator distanceCalculator;
  private final FieldGoalAccuracyCalculator accuracyCalculator;
  private final BlockedByCalculator blockedByCalculator;

  public DefaultFieldGoalSimulator(
    FieldGoalBlockCalculator blockCalculator,
    FieldGoalDistanceCalculator distanceCalculator,
    FieldGoalAccuracyCalculator accuracyCalculator,
    BlockedByCalculator blockedByCalculator
  ) {
    this.blockCalculator = blockCalculator;
    this.distanceCalculator = distanceCalculator;
    this.accuracyCalculator = accuracyCalculator;
    this.blockedByCalculator = blockedByCalculator;
  }

  @Override
  public FieldGoalResult simulate(
    FieldGoalKickPlayCall kickPlayCall,
    FieldGoalBlockPlayCall blockPlayCall
  ) {
    var kickingPersonnel = kickPlayCall.personnel();
    var blockPersonnel = blockPlayCall.personnel();
    var kicker = kickingPersonnel.kicker();

    if (blockCalculator.blocked(kickingPersonnel, blockPersonnel)) {
      return new BlockedFieldGoalResult(
        kicker,
        blockedByCalculator.blockedBy(blockPersonnel)
      );
    }

    return new FieldGoalAttemptResult(
      kicker,
      distanceCalculator.calculate(kicker),
      accuracyCalculator.made(kicker)
    );
  }
}
