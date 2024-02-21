package com.tiernebre.game_simulation.play.field_goal;

import com.tiernebre.game_simulation.dto.personnel.FieldGoalKickingPersonnel;
import com.tiernebre.game_simulation.play.call.OffensivePlayCall;

public final class FieldGoalKickPlayCall extends OffensivePlayCall {

  private final FieldGoalKickingPersonnel personnel;

  public FieldGoalKickPlayCall(FieldGoalKickingPersonnel personnel) {
    this.personnel = personnel;
  }

  public FieldGoalKickingPersonnel personnel() {
    return personnel;
  }
}
