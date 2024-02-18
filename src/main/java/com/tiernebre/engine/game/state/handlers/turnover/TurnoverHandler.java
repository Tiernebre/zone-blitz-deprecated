package com.tiernebre.engine.game.state.handlers.turnover;

import com.tiernebre.engine.dto.game.GameState;

public interface TurnoverHandler {
  public GameState onDowns(GameState state);
}
