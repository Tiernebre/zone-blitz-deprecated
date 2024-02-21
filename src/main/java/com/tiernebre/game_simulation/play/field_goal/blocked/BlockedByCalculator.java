package com.tiernebre.game_simulation.play.field_goal.blocked;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.personnel.FieldGoalBlockPersonnel;

public interface BlockedByCalculator {
  public Player blockedBy(FieldGoalBlockPersonnel blockPersonnel);
}
