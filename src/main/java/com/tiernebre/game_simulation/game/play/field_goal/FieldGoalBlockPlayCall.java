package com.tiernebre.game_simulation.game.play.field_goal;

import com.tiernebre.game_simulation.dto.personnel.FieldGoalBlockPersonnel;
import com.tiernebre.game_simulation.game.play.call.DefensivePlayCall;

public final class FieldGoalBlockPlayCall extends DefensivePlayCall {

  private final FieldGoalBlockPersonnel personnel;

  public FieldGoalBlockPlayCall(FieldGoalBlockPersonnel personnel) {
    this.personnel = personnel;
  }

  public FieldGoalBlockPersonnel personnel() {
    return personnel;
  }
}
