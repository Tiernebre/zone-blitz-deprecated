package com.tiernebre.game_simulation.game.state.handlers.punt;

import com.tiernebre.game_simulation.dto.game.GameState;
import com.tiernebre.game_simulation.play.punt.PuntResult;

public interface PuntHandler {
  public GameState handle(GameState state, PuntResult result);
}
