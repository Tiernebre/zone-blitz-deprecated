package com.tiernebre.engine.play.regular.turnover;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.play.regular.RegularPlayResult;

public class TurnoverPlayResult extends RegularPlayResult {

  private final Player recoveredBy;
  private final Player committedBy;

  public TurnoverPlayResult(
    int yards,
    Player tackledBy,
    Player recoveredBy,
    Player committedBy
  ) {
    super(yards, tackledBy);
    this.recoveredBy = recoveredBy;
    this.committedBy = committedBy;
  }

  public Player committedBy() {
    return committedBy;
  }

  public Player recoveredBy() {
    return recoveredBy;
  }
}
