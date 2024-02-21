package com.tiernebre.game_simulation.play.punt;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.play.PlayResult;

public abstract class PuntResult extends PlayResult {

  private final Player punter;

  public PuntResult(Player punter) {
    this.punter = punter;
  }

  public Player punter() {
    return punter;
  }
}
