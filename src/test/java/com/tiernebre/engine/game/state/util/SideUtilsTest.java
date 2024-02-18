package com.tiernebre.engine.game.state.util;

import static org.junit.Assert.assertEquals;

import com.tiernebre.engine.dto.game.Side;
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
