package com.tiernebre.game_simulation.play.field_goal.blocked;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.personnel.FieldGoalBlockPersonnel;
import java.util.Random;

public final class RandomBlockedByCalculator implements BlockedByCalculator {

  private final Random random;

  public RandomBlockedByCalculator(Random random) {
    this.random = random;
  }

  @Override
  public Player blockedBy(FieldGoalBlockPersonnel blockPersonnel) {
    var blitzers = blockPersonnel.blitzers();
    return blitzers[random.nextInt(blitzers.length)];
  }
}
