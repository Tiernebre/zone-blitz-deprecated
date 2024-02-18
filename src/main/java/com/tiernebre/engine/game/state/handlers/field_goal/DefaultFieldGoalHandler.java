package com.tiernebre.engine.game.state.handlers.field_goal;

import com.tiernebre.engine.EngineConstants;
import com.tiernebre.engine.dto.game.GameState;
import com.tiernebre.engine.dto.game.Score;
import com.tiernebre.engine.game.state.handlers.scoring.ScoringPlayHandler;
import com.tiernebre.engine.game.state.handlers.turnover.TurnoverHandler;
import com.tiernebre.engine.game.state.util.DirectionUtils;
import com.tiernebre.engine.game.state.util.YardNormalizer;
import com.tiernebre.engine.play.field_goal.blocked.BlockedFieldGoalResult;
import com.tiernebre.engine.play.field_goal.result.FieldGoalAttemptResult;
import com.tiernebre.engine.play.field_goal.result.FieldGoalResult;

public final class DefaultFieldGoalHandler implements FieldGoalHandler {

  private final TurnoverHandler turnoverHandler;
  private final ScoringPlayHandler scoringPlayHandler;

  public DefaultFieldGoalHandler(
    TurnoverHandler turnoverHandler,
    ScoringPlayHandler scoringPlayHandler
  ) {
    this.turnoverHandler = turnoverHandler;
    this.scoringPlayHandler = scoringPlayHandler;
  }

  @Override
  public GameState handle(GameState state, FieldGoalResult result) {
    if (result instanceof BlockedFieldGoalResult) {
      return turnoverHandler.onDowns(state);
    }
    var fieldGoalAttempt = (FieldGoalAttemptResult) result;
    if (!fieldGoalAttempt.accurate()) {
      return turnoverHandler.onDowns(state);
    }
    var goalPostYardage = DirectionUtils.isEast(state)
      ? EngineConstants.EAST_GOAL_POST_YARD_LINE
      : EngineConstants.WEST_GOAL_POST_YARD_LINE;
    var attemptYardage =
      state.drive().lineOfScrimmage() -
      YardNormalizer.normalize(
        state,
        EngineConstants.FIELD_GOAL_YARDS_BEHIND_LINE_ATTEMPT
      );
    var kickLandedYardage =
      attemptYardage +
      YardNormalizer.normalize(state, fieldGoalAttempt.distance());

    var made = DirectionUtils.isEast(state)
      ? kickLandedYardage >= goalPostYardage
      : kickLandedYardage <= goalPostYardage;
    return made
      ? scoringPlayHandler.handle(state, Score.FIELD_GOAL)
      : turnoverHandler.onDowns(state);
  }
}
