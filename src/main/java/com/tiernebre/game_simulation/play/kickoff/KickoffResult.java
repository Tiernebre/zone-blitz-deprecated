package com.tiernebre.game_simulation.play.kickoff;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.play.PlayResult;

public abstract class KickoffResult extends PlayResult {

  private final Player kicker;
  private final int distance;

  public KickoffResult(Player kicker, int distance) {
    this.kicker = kicker;
    this.distance = distance;
  }

  public Player kicker() {
    return kicker;
  }

  public int distance() {
    return distance;
  }
}
