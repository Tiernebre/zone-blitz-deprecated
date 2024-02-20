package com.tiernebre.game_simulation.play.regular.pass;

import com.tiernebre.game_simulation.play.regular.RegularPlayResult;
import com.tiernebre.game_simulation.play.regular.RegularPlaySimulatorArguments;
import com.tiernebre.game_simulation.play.regular.defense.TackledByCalculator;
import com.tiernebre.game_simulation.play.regular.pass.completion.CompletedPassCalculator;
import com.tiernebre.game_simulation.play.regular.pass.interception.InterceptionCalculator;
import com.tiernebre.game_simulation.play.regular.pass.interception.InterceptionResult;

public final class DefaultPassingSimulator implements PassingSimulator {

  private final PassingYardageCalculator yardageCalculator;
  private final CompletedPassCalculator completedPassCalculator;
  private final InterceptionCalculator interceptionCalculator;
  private final TackledByCalculator tackledByCalculator;

  public DefaultPassingSimulator(
    PassingYardageCalculator yardageCalculator,
    CompletedPassCalculator completedPassCalculator,
    InterceptionCalculator interceptionCalculator,
    TackledByCalculator tackledByCalculator
  ) {
    this.yardageCalculator = yardageCalculator;
    this.completedPassCalculator = completedPassCalculator;
    this.interceptionCalculator = interceptionCalculator;
    this.tackledByCalculator = tackledByCalculator;
  }

  @Override
  public RegularPlayResult simulate(RegularPlaySimulatorArguments arguments) {
    var offensiveDecision = arguments.offensiveDecision();
    var offensivePersonnel = offensiveDecision.personnel();
    var thrower = offensivePersonnel.quarterback();

    if (interceptionCalculator.intercepted(thrower)) {
      return new InterceptionResult(
        0,
        tackledByCalculator.tackledBy(offensivePersonnel),
        null,
        thrower
      );
    }

    var completed = completedPassCalculator.completed(arguments);
    return new PassResult(
      yardageCalculator.calculate(arguments),
      completed
        ? tackledByCalculator.tackledBy(arguments.defensivePersonnel())
        : null,
      thrower,
      offensiveDecision.target(),
      completed
    );
  }
}
