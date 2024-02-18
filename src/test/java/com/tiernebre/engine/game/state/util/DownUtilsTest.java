package com.tiernebre.engine.game.state.util;

import static org.junit.Assert.assertEquals;

import com.tiernebre.engine.dto.game.Down;
import org.junit.Test;

public class DownUtilsTest {

  @Test
  public void returnsSecondForFirstDown() {
    assertEquals(Down.SECOND, DownUtils.nextDown(Down.FIRST));
  }

  @Test
  public void returnsThirdForSecondDown() {
    assertEquals(Down.THIRD, DownUtils.nextDown(Down.SECOND));
  }

  @Test
  public void returnsFourthForThirdDown() {
    assertEquals(Down.FOURTH, DownUtils.nextDown(Down.THIRD));
  }

  @Test
  public void returnsFirstForFourthDown() {
    assertEquals(Down.FIRST, DownUtils.nextDown(Down.FOURTH));
  }
}
