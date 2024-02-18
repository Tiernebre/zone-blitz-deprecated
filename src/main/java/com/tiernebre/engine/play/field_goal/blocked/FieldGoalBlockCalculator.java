package com.tiernebre.engine.play.field_goal.blocked;

import com.tiernebre.engine.dto.personnel.FieldGoalBlockPersonnel;
import com.tiernebre.engine.dto.personnel.FieldGoalKickingPersonnel;

public interface FieldGoalBlockCalculator {
  public boolean blocked(
    FieldGoalKickingPersonnel kickingPersonnel,
    FieldGoalBlockPersonnel blockPersonnel
  );
}
