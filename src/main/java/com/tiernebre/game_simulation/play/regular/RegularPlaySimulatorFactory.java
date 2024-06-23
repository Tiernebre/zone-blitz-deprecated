package com.tiernebre.game_simulation.play.regular;

import com.tiernebre.game_simulation.GameSimulationCommonDependencies;
import com.tiernebre.game_simulation.play.regular.offensive_target.OffensiveTargetDeciderFactory;
import com.tiernebre.game_simulation.play.regular.pass.PassingSimulatorFactory;
import com.tiernebre.game_simulation.play.regular.rush.RushingSimulatorFactory;

public final class RegularPlaySimulatorFactory {

  public RegularPlaySimulator create(
    GameSimulationCommonDependencies dependencies
  ) {
    return new DefaultRegularPlaySimulator(
      new PassingSimulatorFactory().create(dependencies),
      new RushingSimulatorFactory().create(dependencies),
      new OffensiveTargetDeciderFactory().create()
    );
  }
}
