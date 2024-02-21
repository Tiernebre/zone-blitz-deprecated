package com.tiernebre.game_simulation.play.regular.pass.completion;

import com.tiernebre.game_simulation.dto.Player;

public interface PassAccuracyCalculator {
  public boolean accurate(Player thrower);
}
