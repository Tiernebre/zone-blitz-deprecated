package com.tiernebre.game_simulation.game.state.util;

import com.tiernebre.game_simulation.dto.game.Side;

public class SideUtils {

  public static Side opposite(Side side) {
    return side == Side.HOME ? Side.AWAY : Side.HOME;
  }
}
