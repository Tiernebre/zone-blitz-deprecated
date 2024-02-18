package com.tiernebre.engine.play.field_goal;

import com.tiernebre.engine.dto.personnel.FieldGoalBlockPersonnel;
import com.tiernebre.engine.play.call.DefensivePlayCall;

public final class FieldGoalBlockPlayCall extends DefensivePlayCall {

  private final FieldGoalBlockPersonnel personnel;

  public FieldGoalBlockPlayCall(FieldGoalBlockPersonnel personnel) {
    this.personnel = personnel;
  }

  public FieldGoalBlockPersonnel personnel() {
    return personnel;
  }
}
