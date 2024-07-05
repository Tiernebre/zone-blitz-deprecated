package com.tiernebre.game_simulation.game.play.regular.turnover;

import com.tiernebre.game_simulation.dto.Player;

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
