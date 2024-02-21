package com.tiernebre.game_simulation.game.state.handlers.scoring;

import com.tiernebre.game_simulation.dto.game.GameState;
import com.tiernebre.game_simulation.dto.game.Score;

public interface ScoringPlayHandler {
  public GameState handle(GameState state, Score score);
}
