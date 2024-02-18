package com.tiernebre.engine.play.regular.pass;

import com.tiernebre.engine.play.regular.RegularPlayResult;
import com.tiernebre.engine.play.regular.RegularPlaySimulatorArguments;

public interface PassingSimulator {
  public RegularPlayResult simulate(RegularPlaySimulatorArguments arguments);
}
