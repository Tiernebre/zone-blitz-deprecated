package com.tiernebre.engine.play.regular.rush;

import com.tiernebre.engine.play.regular.RegularPlayResult;
import com.tiernebre.engine.play.regular.RegularPlaySimulatorArguments;

public interface RushingSimulator {
  public RegularPlayResult simulate(RegularPlaySimulatorArguments arguments);
}
