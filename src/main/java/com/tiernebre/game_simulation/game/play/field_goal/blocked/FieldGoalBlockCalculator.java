package com.tiernebre.game_simulation.game.play.field_goal.blocked;

import com.tiernebre.game_simulation.dto.personnel.FieldGoalBlockPersonnel;
import com.tiernebre.game_simulation.dto.personnel.FieldGoalKickingPersonnel;

public interface FieldGoalBlockCalculator {
  public boolean blocked(
    FieldGoalKickingPersonnel kickingPersonnel,
    FieldGoalBlockPersonnel blockPersonnel
  );
}
