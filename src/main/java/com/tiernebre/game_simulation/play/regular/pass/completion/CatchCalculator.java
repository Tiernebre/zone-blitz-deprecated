package com.tiernebre.game_simulation.play.regular.pass.completion;

import com.tiernebre.game_simulation.dto.Player;

public interface CatchCalculator {
  boolean caught(Player receiver, Player[] defenders);
}
