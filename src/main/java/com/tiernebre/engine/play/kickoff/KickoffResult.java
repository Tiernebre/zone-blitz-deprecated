package com.tiernebre.engine.play.kickoff;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.play.PlayResult;

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
