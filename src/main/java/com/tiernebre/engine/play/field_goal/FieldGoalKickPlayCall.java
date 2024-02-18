package com.tiernebre.engine.play.field_goal;

import com.tiernebre.engine.dto.personnel.FieldGoalKickingPersonnel;
import com.tiernebre.engine.play.call.OffensivePlayCall;

public final class FieldGoalKickPlayCall extends OffensivePlayCall {

  private final FieldGoalKickingPersonnel personnel;

  public FieldGoalKickPlayCall(FieldGoalKickingPersonnel personnel) {
    this.personnel = personnel;
  }

  public FieldGoalKickingPersonnel personnel() {
    return personnel;
  }
}
