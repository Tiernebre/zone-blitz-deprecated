package com.tiernebre.engine.play.punt;

import com.tiernebre.engine.dto.Player;

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
