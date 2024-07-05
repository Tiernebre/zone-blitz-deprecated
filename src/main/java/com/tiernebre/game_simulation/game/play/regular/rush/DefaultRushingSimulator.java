package com.tiernebre.game_simulation.game.play.regular.rush;

import com.tiernebre.game_simulation.game.play.regular.RegularPlayResult;
import com.tiernebre.game_simulation.game.play.regular.RegularPlaySimulatorArguments;
import com.tiernebre.game_simulation.game.play.regular.defense.TackledByCalculator;
import com.tiernebre.game_simulation.game.play.regular.turnover.FumbleCalculator;
import com.tiernebre.game_simulation.game.play.regular.turnover.FumbleResult;

public final class DefaultRushingSimulator implements RushingSimulator {

  private final RushingYardageCalculator yardageCalculator;
  private final FumbleCalculator fumbleCalculator;
  private final TackledByCalculator tackledByCalculator;

  public DefaultRushingSimulator(
    RushingYardageCalculator yardageCalculator,
    FumbleCalculator fumbleCalculator,
    TackledByCalculator tackledByCalculator
  ) {
    this.yardageCalculator = yardageCalculator;
    this.fumbleCalculator = fumbleCalculator;
    this.tackledByCalculator = tackledByCalculator;
  }

  @Override
  public RegularPlayResult simulate(RegularPlaySimulatorArguments arguments) {
    var carrier = arguments.offensiveDecision().target();
    if (fumbleCalculator.occurred(carrier, arguments.defensivePersonnel())) {
      return new FumbleResult(
        0,
        tackledByCalculator.tackledBy(
          arguments.offensiveDecision().personnel()
        ),
        null,
        carrier,
        false
      );
    } else {
      return new RushResult(
        yardageCalculator.calculate(arguments),
        tackledByCalculator.tackledBy(arguments.defensivePersonnel()),
        carrier
      );
    }
  }
}
