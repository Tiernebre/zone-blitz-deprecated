package com.tiernebre.engine.play.punt;

import com.tiernebre.engine.dto.Player;

public class PuntAttemptResult extends PuntResult {

  private final int distance;
  private final Player returner;

  public PuntAttemptResult(Player punter, int distance, Player returner) {
    super(punter);
    this.distance = distance;
    this.returner = returner;
  }

  public int distance() {
    return distance;
  }

  public Player returner() {
    return returner;
  }
}
