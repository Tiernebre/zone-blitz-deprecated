package com.tiernebre.engine.game.state;

import com.tiernebre.engine.dto.game.Game;
import com.tiernebre.engine.play.PlayResult;

public interface GameStateMachine {
  public Game handlePlayResult(Game game, PlayResult playResult);
}
