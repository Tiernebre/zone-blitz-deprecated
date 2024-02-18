package com.tiernebre.engine.game.state.handlers.field_goal;

import com.tiernebre.engine.dto.game.GameState;
import com.tiernebre.engine.play.field_goal.result.FieldGoalResult;

public interface FieldGoalHandler {
  public GameState handle(GameState gameState, FieldGoalResult result);
}
