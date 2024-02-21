package com.tiernebre.game_simulation.play.punt;

public class DefaultPuntSimulator implements PuntSimulator {

  private final PuntDistanceCalculator distanceCalculator;

  public DefaultPuntSimulator(PuntDistanceCalculator distanceCalculator) {
    this.distanceCalculator = distanceCalculator;
  }

  @Override
  public PuntResult simulate(
    PuntOffensivePlaycall offensivePlaycall,
    PuntDefensivePlayCall defensivePlayCall
  ) {
    var punter = offensivePlaycall.personnel().punter();
    return new PuntAttemptResult(
      punter,
      distanceCalculator.distance(punter),
      defensivePlayCall.personnel().returner()
    );
  }
}
