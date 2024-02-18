package com.tiernebre.engine.play.field_goal.blocked;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.play.field_goal.result.FieldGoalResult;

public final class BlockedFieldGoalResult extends FieldGoalResult {

  private final Player blockedBy;

  public BlockedFieldGoalResult(Player kicker, Player blockedBy) {
    super(kicker);
    this.blockedBy = blockedBy;
  }

  public Player blockedBy() {
    return blockedBy;
  }
}
