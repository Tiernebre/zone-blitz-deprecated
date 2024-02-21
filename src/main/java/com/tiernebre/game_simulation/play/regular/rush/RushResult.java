package com.tiernebre.game_simulation.play.regular.rush;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.play.regular.RegularPlayResult;

public final class RushResult extends RegularPlayResult {

  private final Player carrier;

  public RushResult(int yards, Player tackledBy, Player carrier) {
    super(yards, tackledBy);
    this.carrier = carrier;
  }

  public Player carrier() {
    return carrier;
  }

  @Override
  public String toString() {
    return String.format("%s ran for %d yards.", carrier, super.yards());
  }
}
