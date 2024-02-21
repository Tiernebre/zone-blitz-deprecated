package com.tiernebre.game_simulation.play.regular.pass.interception;

import com.tiernebre.game_simulation.dto.Player;

public interface InterceptionCalculator {
  public boolean intercepted(Player thrower);
}
