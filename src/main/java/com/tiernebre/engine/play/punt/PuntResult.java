package com.tiernebre.engine.play.punt;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.play.PlayResult;

public abstract class PuntResult extends PlayResult {

  private final Player punter;

  public PuntResult(Player punter) {
    this.punter = punter;
  }

  public Player punter() {
    return punter;
  }
}
