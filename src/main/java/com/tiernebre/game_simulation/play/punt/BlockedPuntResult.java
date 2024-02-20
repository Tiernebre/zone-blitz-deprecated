package com.tiernebre.game_simulation.play.punt;

import com.tiernebre.game_simulation.dto.Player;

public final class BlockedPuntResult extends PuntResult {

  private final Player blockedBy;

  public BlockedPuntResult(Player punter, Player blockedBy) {
    super(punter);
    this.blockedBy = blockedBy;
  }

  public Player blockedBy() {
    return blockedBy;
  }
}
