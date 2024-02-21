package com.tiernebre.game_simulation.game.state.util;

import com.tiernebre.game_simulation.dto.game.Direction;
import com.tiernebre.game_simulation.dto.game.GameState;

public final class YardNormalizer {

  public static int normalize(GameState gameState, int yards) {
    return DirectionUtils.isEast(gameState) ? yards : -yards;
  }

  public static int normalize(Direction direction, int yards) {
    return DirectionUtils.isEast(direction) ? yards : -yards;
  }
}
