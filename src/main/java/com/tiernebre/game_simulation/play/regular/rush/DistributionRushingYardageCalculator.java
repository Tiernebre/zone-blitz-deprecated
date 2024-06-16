package com.tiernebre.game_simulation.play.regular.rush;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.game_simulation.play.regular.RegularPlayOffensiveDecision;
import com.tiernebre.game_simulation.play.regular.RegularPlayPersonnelUtils;
import com.tiernebre.game_simulation.play.regular.RegularPlaySimulatorArguments;
import org.apache.commons.math3.distribution.NormalDistribution;

public class DistributionRushingYardageCalculator
  implements RushingYardageCalculator {

  private final double RUN_BLOCKING_WEIGHT = 0.75;
  private final double RUSHING_ABILITY_WEIGHT = 0.25;
  private final double BLOCK_SHEDDING_WEIGHT = 0.3;
  private final double TACKLING_WEIGHT = 0.7;

  private final NormalDistribution distribution;

  public DistributionRushingYardageCalculator(NormalDistribution distribution) {
    this.distribution = distribution;
  }

  @Override
  public int calculate(RegularPlaySimulatorArguments arguments) {
    return (
      (int) distribution.sample() + calculateYardageAdjustment(arguments)
    );
  }

  private int calculateYardageAdjustment(
    RegularPlaySimulatorArguments arguments
  ) {
    double factor =
      (calculateOffensiveRating(arguments.offensiveDecision()) -
        calculateDefensiveRating(arguments.defensivePersonnel())) /
      (double) EngineConstants.MAXIMUM_PLAYER_ATTRIBUTE;
    return (int) (factor * distribution.getStandardDeviation());
  }

  private int calculateOffensiveRating(RegularPlayOffensiveDecision decision) {
    int runBlocking = (int) (RegularPlayPersonnelUtils.getAverageAttribute(
        decision.personnel().offensiveLinemen(),
        attributes -> attributes.runBlocking()
      ) *
      RUN_BLOCKING_WEIGHT);
    int rushingAbility = (int) (decision
        .target()
        .attributes()
        .rushingAbility() *
      RUSHING_ABILITY_WEIGHT);
    return runBlocking + rushingAbility;
  }

  private int calculateDefensiveRating(
    RegularPlayDefensivePersonnel defensivePersonnel
  ) {
    int blockShedding = (int) (RegularPlayPersonnelUtils.getAverageAttribute(
        defensivePersonnel,
        attributes -> attributes.blockShedding()
      ) *
      BLOCK_SHEDDING_WEIGHT);
    int tackling = (int) (RegularPlayPersonnelUtils.getAverageAttribute(
        defensivePersonnel,
        attributes -> attributes.tackling()
      ) *
      TACKLING_WEIGHT);
    return blockShedding + tackling;
  }
}
