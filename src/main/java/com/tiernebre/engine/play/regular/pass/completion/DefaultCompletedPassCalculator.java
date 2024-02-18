package com.tiernebre.engine.play.regular.pass.completion;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.play.regular.RegularPlaySimulatorArguments;

public class DefaultCompletedPassCalculator implements CompletedPassCalculator {

  private final PassAccuracyCalculator passAccuracyCalculator;
  private final CatchCalculator catchCalculator;

  public DefaultCompletedPassCalculator(
    PassAccuracyCalculator passAccuracyCalculator,
    CatchCalculator catchCalculator
  ) {
    this.passAccuracyCalculator = passAccuracyCalculator;
    this.catchCalculator = catchCalculator;
  }

  @Override
  public boolean completed(RegularPlaySimulatorArguments arguments) {
    var offensiveDecision = arguments.offensiveDecision();
    return (
      passAccuracyCalculator.accurate(
        offensiveDecision.personnel().quarterback()
      ) &&
      catchCalculator.caught(offensiveDecision.target(), new Player[] {})
    );
  }
}
