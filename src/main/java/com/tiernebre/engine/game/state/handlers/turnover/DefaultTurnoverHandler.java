package com.tiernebre.engine.game.state.handlers.turnover;

import com.tiernebre.engine.EngineConstants;
import com.tiernebre.engine.dto.game.Down;
import com.tiernebre.engine.dto.game.Drive;
import com.tiernebre.engine.dto.game.GameState;
import com.tiernebre.engine.game.state.util.DirectionUtils;
import com.tiernebre.engine.game.state.util.SideUtils;
import com.tiernebre.engine.game.state.util.YardNormalizer;

public class DefaultTurnoverHandler implements TurnoverHandler {

  @Override
  public GameState onDowns(GameState state) {
    var drive = state.drive();
    var direction = DirectionUtils.opposite(drive.direction());
    var lineToGain =
      drive.lineOfScrimmage() +
      YardNormalizer.normalize(direction, EngineConstants.DEFAULT_YARDS_TO_GO);
    return new GameState(
      new Drive(Down.FIRST, drive.lineOfScrimmage(), lineToGain, direction),
      state.score(),
      state.time(),
      state.coinTossDecision(),
      SideUtils.opposite(state.onOffense()),
      false,
      false
    );
  }
}
