package com.tiernebre.game_simulation.game.state.handlers.turnover;

import com.tiernebre.game_simulation.dto.game.GameState;

public interface TurnoverHandler {
  public GameState onDowns(GameState state);
}
