package com.tiernebre.game_simulation.game.state.handlers.regular;

import com.tiernebre.game_simulation.dto.game.GameState;
import com.tiernebre.game_simulation.play.regular.RegularPlayResult;

public interface RegularPlayHandler {
  public GameState handle(GameState state, RegularPlayResult result);
}
