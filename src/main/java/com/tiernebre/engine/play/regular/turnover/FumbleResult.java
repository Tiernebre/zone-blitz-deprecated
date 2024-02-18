package com.tiernebre.engine.play.regular.turnover;

import com.tiernebre.engine.dto.Player;

public final class FumbleResult extends TurnoverPlayResult {

  private final boolean lost;

  public FumbleResult(
    int yards,
    Player tackledBy,
    Player recoveredBy,
    Player committedBy,
    boolean lost
  ) {
    super(yards, tackledBy, recoveredBy, committedBy);
    this.lost = lost;
  }

  public boolean lost() {
    return lost;
  }
}
