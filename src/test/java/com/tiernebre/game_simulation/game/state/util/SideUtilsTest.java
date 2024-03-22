package com.tiernebre.game_simulation.game.state.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tiernebre.game_simulation.dto.game.Side;
import org.junit.jupiter.api.Test;

public class SideUtilsTest {

  @Test
  public void oppositeReturnsAwayGivenHome() {
    assertEquals(Side.AWAY, SideUtils.opposite(Side.HOME));
  }

  @Test
  public void oppositeReturnsHomeGivenAway() {
    assertEquals(Side.HOME, SideUtils.opposite(Side.AWAY));
  }
}
