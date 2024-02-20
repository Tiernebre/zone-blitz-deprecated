package com.tiernebre.game_simulation.game.state.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tiernebre.game_simulation.dto.game.Direction;
import com.tiernebre.game_simulation.dto.game.Drive;
import com.tiernebre.game_simulation.dto.game.GameState;
import com.tiernebre.game_simulation.game.state.util.DirectionUtils;
import org.junit.Test;

public class DirectionUtilsTest {

  @Test
  public void isEastReturnsTrueForEast() {
    assertTrue(DirectionUtils.isEast(Direction.EAST));
  }

  @Test
  public void isEastReturnsFalseForWest() {
    assertFalse(DirectionUtils.isEast(Direction.WEST));
  }

  @Test
  public void isEastWithDriveReturnsTrueForEast() {
    var drive = mock(Drive.class);
    when(drive.direction()).thenReturn(Direction.EAST);
    assertTrue(DirectionUtils.isEast(drive));
  }

  @Test
  public void isEastWithDriveReturnsFalseForWest() {
    var drive = mock(Drive.class);
    when(drive.direction()).thenReturn(Direction.WEST);
    assertFalse(DirectionUtils.isEast(drive));
  }

  @Test
  public void isEastWithStateReturnsTrueForEast() {
    var drive = mock(Drive.class);
    var state = mock(GameState.class);
    when(drive.direction()).thenReturn(Direction.EAST);
    when(state.drive()).thenReturn(drive);
    assertTrue(DirectionUtils.isEast(state));
  }

  @Test
  public void isEastWithStateReturnsFalseForWest() {
    var drive = mock(Drive.class);
    var state = mock(GameState.class);
    when(drive.direction()).thenReturn(Direction.WEST);
    when(state.drive()).thenReturn(drive);
    assertFalse(DirectionUtils.isEast(state));
  }
}
