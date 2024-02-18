package com.tiernebre.engine.play.regular.pass.completion;

import com.tiernebre.engine.dto.Player;

public interface CatchCalculator {
  boolean caught(Player receiver, Player[] defenders);
}
