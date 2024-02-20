package com.tiernebre.game_simulation.game.state.handlers.field_goal;

import com.tiernebre.game_simulation.dto.game.GameState;
import com.tiernebre.game_simulation.play.field_goal.result.FieldGoalResult;

public interface FieldGoalHandler {
  public GameState handle(GameState gameState, FieldGoalResult result);
}
