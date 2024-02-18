package com.tiernebre.engine.game.state.handlers.punt;

import com.tiernebre.engine.dto.game.GameState;
import com.tiernebre.engine.game.state.util.YardNormalizer;
import com.tiernebre.engine.play.punt.BlockedPuntResult;
import com.tiernebre.engine.play.punt.PuntAttemptResult;
import com.tiernebre.engine.play.punt.PuntResult;

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
