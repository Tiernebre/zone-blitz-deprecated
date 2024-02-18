package com.tiernebre.engine.game.state.handlers.kickoff;

import com.tiernebre.engine.dto.game.GameState;
import com.tiernebre.engine.play.kickoff.KickoffResult;

public interface KickoffHandler {
  public GameState handle(GameState state, KickoffResult result);
}
