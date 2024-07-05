package com.tiernebre.game_simulation.game.play.regular.pass.interception;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.game.play.regular.turnover.TurnoverPlayResult;

public final class InterceptionResult extends TurnoverPlayResult {

  public InterceptionResult(
    int yards,
    Player tackledBy,
    Player recoveredBy,
    Player committedBy
  ) {
    super(yards, tackledBy, recoveredBy, committedBy);
  }
}
