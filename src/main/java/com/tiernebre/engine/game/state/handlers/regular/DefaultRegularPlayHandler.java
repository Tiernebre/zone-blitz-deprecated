package com.tiernebre.engine.game.state.handlers.regular;

import com.tiernebre.engine.EngineConstants;
import com.tiernebre.engine.dto.game.Down;
import com.tiernebre.engine.dto.game.Drive;
import com.tiernebre.engine.dto.game.GameState;
import com.tiernebre.engine.dto.game.Score;
import com.tiernebre.engine.game.state.handlers.scoring.ScoringPlayHandler;
import com.tiernebre.engine.game.state.handlers.turnover.TurnoverHandler;
import com.tiernebre.engine.game.state.util.DirectionUtils;
import com.tiernebre.engine.game.state.util.DownUtils;
import com.tiernebre.engine.game.state.util.YardNormalizer;
import com.tiernebre.engine.play.regular.RegularPlayResult;

public final class DefaultRegularPlayHandler implements RegularPlayHandler {

  private final ScoringPlayHandler scoringPlayHandler;
  private final TurnoverHandler turnoverHandler;

  public DefaultRegularPlayHandler(
    ScoringPlayHandler scoringPlayHandler,
    TurnoverHandler turnoverHandler
  ) {
    this.scoringPlayHandler = scoringPlayHandler;
    this.turnoverHandler = turnoverHandler;
  }

  @Override
  public GameState handle(GameState state, RegularPlayResult result) {
    var drive = state.drive();
    var lineToGain = drive.lineToGain();

    var newLineOfScrimmage =
      drive.lineOfScrimmage() + YardNormalizer.normalize(state, result.yards());
    var goingEast = DirectionUtils.isEast(state);
    var isTouchdown =
      (goingEast &&
        newLineOfScrimmage >= EngineConstants.EAST_END_ZONE_YARD_LINE) ||
      (!goingEast &&
        newLineOfScrimmage <= EngineConstants.WEST_END_ZONE_YARD_LINE);

    if (isTouchdown) {
      return scoringPlayHandler.handle(state, Score.TOUCHDOWN);
    }

    var pastLineToGain = goingEast
      ? newLineOfScrimmage >= lineToGain
      : newLineOfScrimmage <= lineToGain;

    Down down;
    int newLineToGain;
    if (pastLineToGain) {
      down = Down.FIRST;
      newLineToGain =
        lineToGain +
        YardNormalizer.normalize(state, EngineConstants.DEFAULT_YARDS_TO_GO);
    } else {
      if (drive.down() == Down.FOURTH) {
        return turnoverHandler.onDowns(state);
      }
      down = DownUtils.nextDown(drive.down());
      newLineToGain = lineToGain;
    }
    return new GameState(
      new Drive(down, newLineOfScrimmage, newLineToGain, drive.direction()),
      state.score(),
      state.time(),
      state.coinTossDecision(),
      state.onOffense(),
      state.isKickoff(),
      state.isExtraPointAttempt()
    );
  }
}
