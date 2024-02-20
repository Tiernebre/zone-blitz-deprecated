package com.tiernebre.game_simulation.play.regular.pass;

import com.tiernebre.game_simulation.play.regular.RegularPlayResult;
import com.tiernebre.game_simulation.play.regular.RegularPlaySimulatorArguments;

public interface PassingSimulator {
  public RegularPlayResult simulate(RegularPlaySimulatorArguments arguments);
}
