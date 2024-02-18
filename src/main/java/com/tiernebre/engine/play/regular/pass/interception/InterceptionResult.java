package com.tiernebre.engine.play.regular.pass.interception;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.play.regular.turnover.TurnoverPlayResult;

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
