package com.tiernebre.game_simulation.game.play.kickoff;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.game.play.regular.RegularPlayResult;

public final class KickoffReturnResult extends KickoffResult {

  private final RegularPlayResult returnAttempt;

  public KickoffReturnResult(
    Player kicker,
    int distance,
    RegularPlayResult returnResult
  ) {
    super(kicker, distance);
    this.returnAttempt = returnResult;
  }

  public RegularPlayResult returnAttempt() {
    return returnAttempt;
  }
}
