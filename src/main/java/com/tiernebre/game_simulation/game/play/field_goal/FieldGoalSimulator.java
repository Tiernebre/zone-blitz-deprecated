package com.tiernebre.game_simulation.game.play.field_goal;

import com.tiernebre.game_simulation.game.play.field_goal.result.FieldGoalResult;

public interface FieldGoalSimulator {
  public FieldGoalResult simulate(
    FieldGoalKickPlayCall kickPlayCall,
    FieldGoalBlockPlayCall blockPlayCall
  );
}
