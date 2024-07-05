package com.tiernebre.game_simulation.game.play.kickoff;

import com.tiernebre.game_simulation.game.play.regular.defense.TackledByCalculator;

public final class DefaultKickoffSimulator implements KickoffSimulator {

  private final KickoffDistanceCalculator distanceCalculator;
  private final KickoffReturnAttemptYardageCalculator attemptYardageCalculator;
  private final TackledByCalculator tackledByCalculator;

  public DefaultKickoffSimulator(
    KickoffDistanceCalculator distanceCalculator,
    KickoffReturnAttemptYardageCalculator attemptYardageCalculator,
    TackledByCalculator tackledByCalculator
  ) {
    this.distanceCalculator = distanceCalculator;
    this.attemptYardageCalculator = attemptYardageCalculator;
    this.tackledByCalculator = tackledByCalculator;
  }

  @Override
  public KickoffResult simulate(
    KickReturnPlayCall kickReturnPlayCall,
    KickoffPlayCall kickoffPlayCall
  ) {
    var kickoffPersonnel = kickoffPlayCall.personnel();
    var returner = kickReturnPlayCall.personnel().returner();
    var kicker = kickoffPersonnel.kickOffSpecialist();
    return new KickoffReturnResult(
      kicker,
      distanceCalculator.calculate(kicker),
      new KickoffReturnAttemptResult(
        attemptYardageCalculator.calculate(),
        tackledByCalculator.tackledBy(
          KickoffUtils.getAllKickoffPlayers(kickoffPersonnel)
        ),
        returner
      )
    );
  }
}
