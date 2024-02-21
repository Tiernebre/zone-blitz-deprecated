package com.tiernebre.game_simulation.game.state.handlers.kickoff;

import com.tiernebre.game_simulation.dto.game.GameState;
import com.tiernebre.game_simulation.play.kickoff.KickoffResult;

public interface KickoffHandler {
  public GameState handle(GameState state, KickoffResult result);
}
