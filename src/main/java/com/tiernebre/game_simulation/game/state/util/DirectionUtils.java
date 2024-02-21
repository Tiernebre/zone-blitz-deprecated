package com.tiernebre.game_simulation.game.state.util;

import com.tiernebre.game_simulation.dto.game.Direction;
import com.tiernebre.game_simulation.dto.game.Drive;
import com.tiernebre.game_simulation.dto.game.GameState;

public final class DirectionUtils {

  public static boolean isEast(GameState gameState) {
    return isEast(gameState.drive());
  }

  public static boolean isEast(Drive drive) {
    return isEast(drive.direction());
  }

  public static boolean isEast(Direction direction) {
    return direction == Direction.EAST;
  }

  public static Direction opposite(Direction direction) {
    return direction == Direction.EAST ? Direction.WEST : Direction.EAST;
  }
}
