package com.tiernebre.game_simulation.play.field_goal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tiernebre.game_simulation.game.play.field_goal.DefaultFieldGoalSimulator;
import com.tiernebre.game_simulation.game.play.field_goal.accuracy.FieldGoalAccuracyCalculator;
import com.tiernebre.game_simulation.game.play.field_goal.blocked.BlockedByCalculator;
import com.tiernebre.game_simulation.game.play.field_goal.blocked.BlockedFieldGoalResult;
import com.tiernebre.game_simulation.game.play.field_goal.blocked.FieldGoalBlockCalculator;
import com.tiernebre.game_simulation.game.play.field_goal.distance.FieldGoalDistanceCalculator;
import com.tiernebre.game_simulation.game.play.field_goal.result.FieldGoalAttemptResult;
import org.junit.jupiter.api.Test;

public class DefaultFieldGoalSimulatorTest {

  private final FieldGoalBlockCalculator blockCalculator = mock(
    FieldGoalBlockCalculator.class
  );
  private final FieldGoalDistanceCalculator distanceCalculator = mock(
    FieldGoalDistanceCalculator.class
  );
  private final FieldGoalAccuracyCalculator accuracyCalculator = mock(
    FieldGoalAccuracyCalculator.class
  );
  private final BlockedByCalculator blockedByCalculator = mock(
    BlockedByCalculator.class
  );

  private final DefaultFieldGoalSimulator defaultFieldGoalSimulator =
    new DefaultFieldGoalSimulator(
      blockCalculator,
      distanceCalculator,
      accuracyCalculator,
      blockedByCalculator
    );

  @Test
  public void returnsABlockedFieldGoal() {
    var kickPlayCall = FieldGoalSimulatorMockFactory.kickPlayCall();
    var blockPlayCall = FieldGoalSimulatorMockFactory.blockPlayCall();
    when(
      blockCalculator.blocked(
        eq(kickPlayCall.personnel()),
        eq(blockPlayCall.personnel())
      )
    ).thenReturn(true);
    var blocker = blockPlayCall.personnel().blitzers()[0];
    when(
      blockedByCalculator.blockedBy(eq(blockPlayCall.personnel()))
    ).thenReturn(blocker);
    var result = defaultFieldGoalSimulator.simulate(
      kickPlayCall,
      blockPlayCall
    );
    assertTrue(result instanceof BlockedFieldGoalResult);
    var blockedResult = (BlockedFieldGoalResult) result;
    assertEquals(kickPlayCall.personnel().kicker(), blockedResult.kicker());
    assertEquals(blocker, blockedResult.blockedBy());
  }

  @Test
  public void returnsAFieldGoalAttempt() {
    var kickPlayCall = FieldGoalSimulatorMockFactory.kickPlayCall();
    var blockPlayCall = FieldGoalSimulatorMockFactory.blockPlayCall();
    var kicker = kickPlayCall.personnel().kicker();
    when(
      blockCalculator.blocked(
        eq(kickPlayCall.personnel()),
        eq(blockPlayCall.personnel())
      )
    ).thenReturn(false);
    var distance = 36;
    var accurate = true;
    when(distanceCalculator.calculate(eq(kicker))).thenReturn(distance);
    when(accuracyCalculator.made(eq(kicker))).thenReturn(accurate);
    var result = defaultFieldGoalSimulator.simulate(
      kickPlayCall,
      blockPlayCall
    );
    assertTrue(result instanceof FieldGoalAttemptResult);
    var attemptResult = (FieldGoalAttemptResult) result;
    assertEquals(kicker, attemptResult.kicker());
    assertEquals(distance, attemptResult.distance());
    assertEquals(accurate, attemptResult.accurate());
  }
}
