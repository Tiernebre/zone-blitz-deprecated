package com.tiernebre.engine.play.regular.pass.completion;

import com.tiernebre.engine.dto.Player;

public interface PassAccuracyCalculator {
  public boolean accurate(Player thrower);
}
