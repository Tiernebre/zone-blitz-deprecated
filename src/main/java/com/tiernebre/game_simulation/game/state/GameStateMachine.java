package com.tiernebre.game_simulation.game.state;

import com.tiernebre.game_simulation.dto.game.Game;
import com.tiernebre.game_simulation.game.play.PlayResult;

public interface GameStateMachine {
  public Game handlePlayResult(Game game, PlayResult playResult);
}
