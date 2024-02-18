package com.tiernebre.engine.game.state.handlers.scoring;

import static org.junit.Assert.assertEquals;

import com.tiernebre.engine.dto.game.Score;
import com.tiernebre.engine.dto.game.ScoringConfiguration;
import com.tiernebre.engine.dto.game.Side;
import com.tiernebre.engine.game.GameMockFactory;
import org.junit.Test;

public class DefaultScoringPlayHandlerTest {

  private final ScoringConfiguration scoringConfiguration =
    new ScoringConfiguration(7, 3, 1, 2, 2);
  private final DefaultScoringPlayHandler scoringPlayHandler =
    new DefaultScoringPlayHandler(scoringConfiguration);

  @Test
  public void handlesTouchdown() {
    var result = scoringPlayHandler.handle(
      GameMockFactory.state(),
      Score.TOUCHDOWN
    );
    assertEquals(result.score().home(), scoringConfiguration.touchdownPoints());
    assertEquals(result.isExtraPointAttempt(), true);
    assertEquals(result.isKickoff(), false);
    assertEquals(result.onOffense(), Side.HOME);
  }

  @Test
  public void handlesFieldGoal() {
    var result = scoringPlayHandler.handle(
      GameMockFactory.state(),
      Score.FIELD_GOAL
    );
    assertEquals(result.score().home(), scoringConfiguration.fieldGoalPoints());
    assertEquals(result.isExtraPointAttempt(), false);
    assertEquals(result.isKickoff(), true);
    assertEquals(result.onOffense(), Side.AWAY);
  }

  @Test
  public void handlesExtraPoint() {
    var result = scoringPlayHandler.handle(
      GameMockFactory.state(),
      Score.EXTRA_POINT
    );
    assertEquals(
      result.score().home(),
      scoringConfiguration.extraPointAttemptPoints()
    );
    assertEquals(result.isExtraPointAttempt(), false);
    assertEquals(result.isKickoff(), true);
    assertEquals(result.onOffense(), Side.AWAY);
  }

  @Test
  public void handlesTwoPoint() {
    var result = scoringPlayHandler.handle(
      GameMockFactory.state(),
      Score.TWO_POINT_CONVERSION
    );
    assertEquals(
      result.score().home(),
      scoringConfiguration.twoPointConversionAttemptPoints()
    );
    assertEquals(result.isExtraPointAttempt(), false);
    assertEquals(result.isKickoff(), true);
    assertEquals(result.onOffense(), Side.AWAY);
  }
}
