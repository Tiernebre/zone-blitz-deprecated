package com.tiernebre.engine.play.regular;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.play.PlayResult;

public abstract class RegularPlayResult extends PlayResult {

  private final int yards;
  private final Player tackledBy;

  public RegularPlayResult(int yards, Player tackledBy) {
    this.yards = yards;
    this.tackledBy = tackledBy;
  }

  public int yards() {
    return this.yards;
  }

  public Player tackledBy() {
    return this.tackledBy;
  }
}
