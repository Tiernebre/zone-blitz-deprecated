package com.tiernebre.game_simulation.game.play;

import com.tiernebre.game_simulation.GameSimulationCommonDependencies;
import com.tiernebre.game_simulation.game.play.field_goal.FieldGoalSimulatorFactory;
import com.tiernebre.game_simulation.game.play.kickoff.KickoffSimulatorFactory;
import com.tiernebre.game_simulation.game.play.punt.PuntSimulatorFactory;
import com.tiernebre.game_simulation.game.play.regular.RegularPlaySimulatorFactory;
import com.tiernebre.game_simulation.game.play.regular.defense.RandomTackledByCalculator;
import com.tiernebre.game_simulation.util.PercentageCalculator;
import java.util.Random;

public final class PlaySimulatorFactory {

  public PlaySimulator create() {
    var random = new Random();
    GameSimulationCommonDependencies dependencies =
      new GameSimulationCommonDependencies(
        random,
        new PercentageCalculator(random),
        new RandomTackledByCalculator(random)
      );
    return new DefaultPlaySimulator(
      new RegularPlaySimulatorFactory().create(dependencies),
      new FieldGoalSimulatorFactory().create(dependencies),
      new PuntSimulatorFactory().create(),
      new KickoffSimulatorFactory().create(dependencies)
    );
  }
}
