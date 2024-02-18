package com.tiernebre.engine.game.state.handlers.field_goal;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tiernebre.engine.EngineConstants;
import com.tiernebre.engine.dto.game.Direction;
import com.tiernebre.engine.dto.game.Down;
import com.tiernebre.engine.dto.game.Drive;
import com.tiernebre.engine.dto.game.Score;
import com.tiernebre.engine.game.GameMockFactory;
import com.tiernebre.engine.game.state.handlers.scoring.ScoringPlayHandler;
import com.tiernebre.engine.game.state.handlers.turnover.TurnoverHandler;
import com.tiernebre.engine.play.PlayResultMockFactory;
import org.junit.Test;

public final class DefaultFieldGoalAttemptHandlerTest {

  private final TurnoverHandler turnoverHandler = mock(TurnoverHandler.class);
  private final ScoringPlayHandler scoringPlayHandler = mock(
    ScoringPlayHandler.class
  );
  private final DefaultFieldGoalHandler defaultFieldGoalAttemptHandler =
    new DefaultFieldGoalHandler(turnoverHandler, scoringPlayHandler);

  @Test
  public void handlesABlockedKick() {
    var state = GameMockFactory.state();
    var result = PlayResultMockFactory.blockedFieldGoalResult();
    var expectedState = GameMockFactory.randomizedState();
    when(turnoverHandler.onDowns(eq(state))).thenReturn(expectedState);
    assertEquals(
      expectedState,
      defaultFieldGoalAttemptHandler.handle(state, result)
    );
  }

  @Test
  public void handlesAMissedKick() {
    var state = GameMockFactory.state();
    var result = PlayResultMockFactory.fieldGoalAttemptResult(50, false);
    var expectedState = GameMockFactory.randomizedState();
    when(turnoverHandler.onDowns(eq(state))).thenReturn(expectedState);
    assertEquals(
      expectedState,
      defaultFieldGoalAttemptHandler.handle(state, result)
    );
  }

  @Test
  public void handlesAWeakFieldGoalAttempt() {
    var state = GameMockFactory.state();
    var result = PlayResultMockFactory.fieldGoalAttemptResult(1, true);
    var expectedState = GameMockFactory.randomizedState();
    when(turnoverHandler.onDowns(eq(state))).thenReturn(expectedState);
    assertEquals(
      expectedState,
      defaultFieldGoalAttemptHandler.handle(state, result)
    );
  }

  @Test
  public void handlesAMadeFieldGoalAttemptGoingEast() {
    var drive = new Drive(
      Down.FOURTH,
      EngineConstants.EAST_END_ZONE_YARD_LINE - 1,
      EngineConstants.EAST_END_ZONE_YARD_LINE,
      Direction.EAST
    );
    var state = GameMockFactory.state(drive);
    var result = PlayResultMockFactory.fieldGoalAttemptResult(100, true);
    var expectedState = GameMockFactory.randomizedState();
    when(scoringPlayHandler.handle(eq(state), eq(Score.FIELD_GOAL)))
      .thenReturn(expectedState);
    assertEquals(
      expectedState,
      defaultFieldGoalAttemptHandler.handle(state, result)
    );
  }

  @Test
  public void handlesAMadeFieldGoalAttemptGoingWest() {
    var drive = new Drive(
      Down.FOURTH,
      EngineConstants.WEST_END_ZONE_YARD_LINE + 1,
      EngineConstants.WEST_END_ZONE_YARD_LINE,
      Direction.WEST
    );
    var state = GameMockFactory.state(drive);
    var result = PlayResultMockFactory.fieldGoalAttemptResult(100, true);
    var expectedState = GameMockFactory.randomizedState();
    when(scoringPlayHandler.handle(eq(state), eq(Score.FIELD_GOAL)))
      .thenReturn(expectedState);
    assertEquals(
      expectedState,
      defaultFieldGoalAttemptHandler.handle(state, result)
    );
  }

  @Test
  public void accountsForKickerLiningUpBehindScrimmage() {
    var drive = new Drive(Down.FOURTH, 0, 10, Direction.EAST);
    var state = GameMockFactory.state(drive);
    var result = PlayResultMockFactory.fieldGoalAttemptResult(66, true);
    var expectedState = GameMockFactory.randomizedState();
    when(turnoverHandler.onDowns(eq(state))).thenReturn(expectedState);
    assertEquals(
      expectedState,
      defaultFieldGoalAttemptHandler.handle(state, result)
    );
  }

  @Test
  public void accountsForEndZoneYardage() {
    var drive = new Drive(Down.FOURTH, 0, 10, Direction.EAST);
    var state = GameMockFactory.state(drive);
    var result = PlayResultMockFactory.fieldGoalAttemptResult(56, true);
    var expectedState = GameMockFactory.randomizedState();
    when(turnoverHandler.onDowns(eq(state))).thenReturn(expectedState);
    assertEquals(
      expectedState,
      defaultFieldGoalAttemptHandler.handle(state, result)
    );
  }

  @Test
  public void barelyMadeFieldGoalCase() {
    var drive = new Drive(Down.FOURTH, 0, 10, Direction.EAST);
    var state = GameMockFactory.state(drive);
    var result = PlayResultMockFactory.fieldGoalAttemptResult(67, true);
    var expectedState = GameMockFactory.randomizedState();
    when(scoringPlayHandler.handle(eq(state), eq(Score.FIELD_GOAL)))
      .thenReturn(expectedState);
    assertEquals(
      expectedState,
      defaultFieldGoalAttemptHandler.handle(state, result)
    );
  }
}
