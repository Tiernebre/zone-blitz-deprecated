package com.tiernebre.engine.game.state.handlers.punt;

import com.tiernebre.engine.dto.game.GameState;
import com.tiernebre.engine.play.punt.PuntResult;

public interface PuntHandler {
  public GameState handle(GameState state, PuntResult result);
}
