package com.tiernebre.engine.game.state.handlers.regular;

import com.tiernebre.engine.dto.game.GameState;
import com.tiernebre.engine.play.regular.RegularPlayResult;

public interface RegularPlayHandler {
  public GameState handle(GameState state, RegularPlayResult result);
}
