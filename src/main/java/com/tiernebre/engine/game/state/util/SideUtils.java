package com.tiernebre.engine.game.state.util;

import com.tiernebre.engine.dto.game.Side;

public class SideUtils {

  public static Side opposite(Side side) {
    return side == Side.HOME ? Side.AWAY : Side.HOME;
  }
}
