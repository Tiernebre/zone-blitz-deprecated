package com.tiernebre.game_simulation.game.state.handlers.kickoff;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.dto.game.Down;
import com.tiernebre.game_simulation.dto.game.Drive;
import com.tiernebre.game_simulation.dto.game.GameState;
import com.tiernebre.game_simulation.game.play.kickoff.KickoffResult;
import com.tiernebre.game_simulation.game.play.kickoff.KickoffTouchbackResult;
import com.tiernebre.game_simulation.game.state.util.DirectionUtils;
import com.tiernebre.game_simulation.game.state.util.YardNormalizer;

public final class DefaultKickoffHandler implements KickoffHandler {

  @Override
  public GameState handle(GameState state, KickoffResult result) {
    var isEast = DirectionUtils.isEast(state);
    var kickoffYardLine = isEast
      ? EngineConstants.EAST_KICKOFF_YARD_LINE
      : EngineConstants.WEST_KICKOFF_YARD_LINE;
    var landingSpot =
      kickoffYardLine +
      YardNormalizer.normalize(
        DirectionUtils.opposite(state.drive().direction()),
        result.distance()
      );
    var overEndZone =
      Math.abs(landingSpot) >= EngineConstants.EAST_GOAL_POST_YARD_LINE;
    if (result instanceof KickoffTouchbackResult || overEndZone) {
      var startingYardLine = isEast
        ? EngineConstants.WEST_TOUCHBACK_STARTING_YARD_LINE
        : EngineConstants.EAST_TOUCHBACK_STARTING_YARD_LINE;
      var lineToGain =
        startingYardLine +
        YardNormalizer.normalize(state, EngineConstants.DEFAULT_YARDS_TO_GO);
      return new GameState(
        new Drive(
          Down.FIRST,
          startingYardLine,
          lineToGain,
          state.drive().direction()
        ),
        state.score(),
        state.time(),
        state.coinTossDecision(),
        state.onOffense(),
        state.isKickoff(),
        state.isExtraPointAttempt()
      );
    }
    return null;
  }
}
