package com.tiernebre.engine.play.field_goal.blocked;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.dto.personnel.FieldGoalBlockPersonnel;

public interface BlockedByCalculator {
  public Player blockedBy(FieldGoalBlockPersonnel blockPersonnel);
}
