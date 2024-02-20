package com.tiernebre.game_simulation.play.regular.pass;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.play.regular.RegularPlayResult;

public final class PassResult extends RegularPlayResult {

  private final Player thrower;
  private final Player target;
  private final boolean completed;

  public PassResult(
    int yards,
    Player tackledBy,
    Player thrower,
    Player target,
    boolean completed
  ) {
    super(yards, tackledBy);
    this.thrower = thrower;
    this.target = target;
    this.completed = completed;
  }

  public Player thrower() {
    return this.thrower;
  }

  public Player target() {
    return this.target;
  }

  public boolean completed() {
    return this.completed;
  }
}
