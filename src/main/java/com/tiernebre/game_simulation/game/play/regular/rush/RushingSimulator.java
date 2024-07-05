package com.tiernebre.game_simulation.game.play.regular.rush;

import com.tiernebre.game_simulation.game.play.regular.RegularPlayResult;
import com.tiernebre.game_simulation.game.play.regular.RegularPlaySimulatorArguments;

public interface RushingSimulator {
  public RegularPlayResult simulate(RegularPlaySimulatorArguments arguments);
}
