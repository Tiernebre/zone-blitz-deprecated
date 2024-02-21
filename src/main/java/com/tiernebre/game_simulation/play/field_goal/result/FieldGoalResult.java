package com.tiernebre.game_simulation.play.field_goal.result;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.play.PlayResult;

public class FieldGoalResult extends PlayResult {

  private final Player kicker;

  public FieldGoalResult(Player kicker) {
    this.kicker = kicker;
  }

  public Player kicker() {
    return kicker;
  }
}
