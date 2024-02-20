package com.tiernebre.game_simulation.game.state.handlers.turnover;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.dto.game.Down;
import com.tiernebre.game_simulation.dto.game.Drive;
import com.tiernebre.game_simulation.dto.game.GameState;
import com.tiernebre.game_simulation.game.state.util.DirectionUtils;
import com.tiernebre.game_simulation.game.state.util.SideUtils;
import com.tiernebre.game_simulation.game.state.util.YardNormalizer;

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
