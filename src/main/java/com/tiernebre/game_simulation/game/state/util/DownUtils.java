package com.tiernebre.game_simulation.game.state.util;

import com.tiernebre.game_simulation.dto.game.Down;

public final class DownUtils {

  public static Down nextDown(Down down) {
    return switch (down) {
      case Down.FIRST -> Down.SECOND;
      case Down.SECOND -> Down.THIRD;
      case Down.THIRD -> Down.FOURTH;
      case Down.FOURTH -> Down.FIRST;
    };
  }
}
