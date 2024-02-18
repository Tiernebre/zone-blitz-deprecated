package com.tiernebre.engine.game.state.handlers.scoring;

import com.tiernebre.engine.dto.game.GameState;
import com.tiernebre.engine.dto.game.Score;

public interface ScoringPlayHandler {
  public GameState handle(GameState state, Score score);
}
