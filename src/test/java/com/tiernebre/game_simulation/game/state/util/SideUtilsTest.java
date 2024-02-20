package com.tiernebre.game_simulation.game.state.util;

import static org.junit.Assert.assertEquals;

import com.tiernebre.game_simulation.dto.game.Side;
import com.tiernebre.game_simulation.game.state.util.SideUtils;
import org.junit.Test;

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
