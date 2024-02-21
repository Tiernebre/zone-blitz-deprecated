package com.tiernebre.game_simulation.play.regular.rush;

import com.tiernebre.game_simulation.play.regular.RegularPlayResult;
import com.tiernebre.game_simulation.play.regular.RegularPlaySimulatorArguments;

public interface RushingSimulator {
  public RegularPlayResult simulate(RegularPlaySimulatorArguments arguments);
}
