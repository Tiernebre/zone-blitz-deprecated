package com.tiernebre.game_simulation.game.state.handlers.regular;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.dto.game.Direction;
import com.tiernebre.game_simulation.dto.game.Down;
import com.tiernebre.game_simulation.dto.game.Drive;
import com.tiernebre.game_simulation.dto.game.Score;
import com.tiernebre.game_simulation.game.GameMockFactory;
import com.tiernebre.game_simulation.game.state.handlers.regular.DefaultRegularPlayHandler;
import com.tiernebre.game_simulation.game.state.handlers.scoring.ScoringPlayHandler;
import com.tiernebre.game_simulation.game.state.handlers.turnover.TurnoverHandler;
import com.tiernebre.game_simulation.play.PlayResultMockFactory;
import org.junit.jupiter.api.Test;

public class DefaultRegularPlayHandlerTest {

  private final ScoringPlayHandler scoringPlayHandler = mock(
    ScoringPlayHandler.class
  );
  private final TurnoverHandler turnoverHandler = mock(TurnoverHandler.class);
  private final DefaultRegularPlayHandler defaultRegularPlayHandler =
    new DefaultRegularPlayHandler(scoringPlayHandler, turnoverHandler);

  @Test
  public void handlesTouchdownCase() {
    int yards = 1;
    var drive = GameMockFactory.drive(
      EngineConstants.EAST_END_ZONE_YARD_LINE - 1
    );
    var state = GameMockFactory.state(drive);
    var result = PlayResultMockFactory.rushResult(yards);
    var newState = GameMockFactory.randomizedState();
    when(scoringPlayHandler.handle(eq(state), eq(Score.TOUCHDOWN))).thenReturn(
      newState
    );
    assertEquals(newState, defaultRegularPlayHandler.handle(state, result));
  }

  @Test
  public void handlesNextDownCase() {
    int yards = 1;
    var state = GameMockFactory.state();
    assertEquals(Down.FIRST, state.drive().down());
    var result = PlayResultMockFactory.rushResult(yards);
    var newState = defaultRegularPlayHandler.handle(state, result);
    assertEquals(Down.SECOND, newState.drive().down());
    assertEquals(
      state.drive().lineOfScrimmage() + yards,
      newState.drive().lineOfScrimmage()
    );
    assertEquals(state.drive().lineToGain(), newState.drive().lineToGain());
  }

  @Test
  public void handlesANewFirstDownCase() {
    var state = GameMockFactory.state();
    int yards = state.drive().lineToGain() - state.drive().lineOfScrimmage();
    assertEquals(Down.FIRST, state.drive().down());
    var result = PlayResultMockFactory.rushResult(yards);
    var newState = defaultRegularPlayHandler.handle(state, result);
    assertEquals(Down.FIRST, newState.drive().down());
    assertEquals(
      state.drive().lineOfScrimmage() + yards,
      newState.drive().lineOfScrimmage()
    );
    assertEquals(
      state.drive().lineToGain() + EngineConstants.DEFAULT_YARDS_TO_GO,
      newState.drive().lineToGain()
    );
  }

  @Test
  public void handlesFailedFourthDownConversionCase() {
    var drive = new Drive(Down.FOURTH, 0, 10, Direction.EAST);
    var state = GameMockFactory.state(drive);
    var result = PlayResultMockFactory.rushResult(0);
    var newState = GameMockFactory.randomizedState();
    when(turnoverHandler.onDowns(eq(state))).thenReturn(newState);
    assertEquals(newState, defaultRegularPlayHandler.handle(state, result));
  }
}
