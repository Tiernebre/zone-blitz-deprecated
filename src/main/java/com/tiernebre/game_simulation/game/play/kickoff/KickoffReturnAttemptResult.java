package com.tiernebre.game_simulation.game.play.kickoff;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.game.play.regular.RegularPlayResult;

public final class KickoffReturnAttemptResult extends RegularPlayResult {

  private final Player returner;

  public KickoffReturnAttemptResult(
    int yards,
    Player tackledBy,
    Player returner
  ) {
    super(yards, tackledBy);
    this.returner = returner;
  }

  public Player returner() {
    return returner;
  }
}
