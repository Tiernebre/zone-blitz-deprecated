package com.tiernebre.game_simulation.game.state.handlers.turnover;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.dto.game.Direction;
import com.tiernebre.game_simulation.dto.game.Down;
import com.tiernebre.game_simulation.dto.game.Drive;
import com.tiernebre.game_simulation.game.GameMockFactory;
import com.tiernebre.game_simulation.game.state.handlers.turnover.DefaultTurnoverHandler;
import com.tiernebre.game_simulation.game.state.util.SideUtils;
import org.junit.jupiter.api.Test;

public class DefaultTurnoverHandlerTest {

  private final DefaultTurnoverHandler defaultTurnoverHandler =
    new DefaultTurnoverHandler();

  @Test
  public void onDownsFlipsSide() {
    var state = GameMockFactory.state();
    var newState = defaultTurnoverHandler.onDowns(state);
    assertEquals(SideUtils.opposite(state.onOffense()), newState.onOffense());
  }

  @Test
  public void onDownsCreatesNewDrive() {
    var drive = new Drive(Down.FOURTH, 0, 10, Direction.EAST);
    var state = GameMockFactory.state(drive);
    var newState = defaultTurnoverHandler.onDowns(state);
    var newDrive = newState.drive();
    assertEquals(Direction.WEST, newDrive.direction());
    assertEquals(Down.FIRST, newDrive.down());
    assertEquals(drive.lineOfScrimmage(), newDrive.lineOfScrimmage());
    assertEquals(-EngineConstants.DEFAULT_YARDS_TO_GO, newDrive.lineToGain());
  }

  @Test
  public void onDownsRetainsCorrectStateValues() {
    var state = GameMockFactory.state();
    var newState = defaultTurnoverHandler.onDowns(state);
    assertEquals(state.score(), newState.score());
    assertEquals(state.time(), newState.time());
    assertEquals(state.coinTossDecision(), newState.coinTossDecision());
  }
}
