package com.tiernebre.game_simulation.game.state.handlers.punt;

import com.tiernebre.game_simulation.dto.game.GameState;
import com.tiernebre.game_simulation.game.state.util.YardNormalizer;
import com.tiernebre.game_simulation.play.punt.BlockedPuntResult;
import com.tiernebre.game_simulation.play.punt.PuntAttemptResult;
import com.tiernebre.game_simulation.play.punt.PuntResult;

public class DefaultPuntHandler implements PuntHandler {

  @Override
  public GameState handle(GameState state, PuntResult result) {
    var drive = state.drive();
    var lineOfScrimmage = drive.lineOfScrimmage();

    if (result instanceof BlockedPuntResult blockedPuntResult) {
      // TODO: handle blocked punts
      return state;
    }

    var attemptedPuntResult = (PuntAttemptResult) result;
    var distance = YardNormalizer.normalize(
      state,
      attemptedPuntResult.distance()
    );
    throw new UnsupportedOperationException("Unimplemented method 'handle'");
  }
}
