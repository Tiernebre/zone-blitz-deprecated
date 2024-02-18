package com.tiernebre.engine.game.state.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tiernebre.engine.dto.game.Direction;
import com.tiernebre.engine.dto.game.Drive;
import com.tiernebre.engine.dto.game.GameState;
import org.junit.Test;

public class YardNormalizerTest {

  @Test
  public void normalizesForEastDirection() {
    var yards = 12;
    var drive = mock(Drive.class);
    var state = mock(GameState.class);
    when(drive.direction()).thenReturn(Direction.EAST);
    when(state.drive()).thenReturn(drive);
    assertEquals(yards, YardNormalizer.normalize(state, yards));
  }

  @Test
  public void normalizesForWestDirection() {
    var yards = 12;
    var drive = mock(Drive.class);
    var state = mock(GameState.class);
    when(drive.direction()).thenReturn(Direction.WEST);
    when(state.drive()).thenReturn(drive);
    assertEquals(-yards, YardNormalizer.normalize(state, yards));
  }
}
