package com.tiernebre.game_simulation.game.play.regular.pass;

import com.tiernebre.game_simulation.game.play.regular.RegularPlayResult;
import com.tiernebre.game_simulation.game.play.regular.RegularPlaySimulatorArguments;

public interface PassingSimulator {
  public RegularPlayResult simulate(RegularPlaySimulatorArguments arguments);
}
