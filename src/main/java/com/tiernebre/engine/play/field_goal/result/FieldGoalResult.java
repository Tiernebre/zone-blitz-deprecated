package com.tiernebre.engine.play.field_goal.result;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.play.PlayResult;

public class FieldGoalResult extends PlayResult {

  private final Player kicker;

  public FieldGoalResult(Player kicker) {
    this.kicker = kicker;
  }

  public Player kicker() {
    return kicker;
  }
}
