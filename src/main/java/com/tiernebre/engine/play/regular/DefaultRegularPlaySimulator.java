package com.tiernebre.engine.play.regular;

import com.tiernebre.engine.play.regular.offensive_target.OffensiveTargetDecider;
import com.tiernebre.engine.play.regular.pass.PassingSimulator;
import com.tiernebre.engine.play.regular.rush.RushingSimulator;

public final class DefaultRegularPlaySimulator implements RegularPlaySimulator {

  private final PassingSimulator passingSimulator;
  private final RushingSimulator rushingSimulator;
  private final OffensiveTargetDecider offensiveTargetDecider;

  public DefaultRegularPlaySimulator(
    PassingSimulator passingSimulator,
    RushingSimulator rushingSimulator,
    OffensiveTargetDecider offensiveTargetDecider
  ) {
    this.passingSimulator = passingSimulator;
    this.rushingSimulator = rushingSimulator;
    this.offensiveTargetDecider = offensiveTargetDecider;
  }

  @Override
  public RegularPlayResult simulate(
    RegularPlayOffensivePlayCall offensivePlayCall,
    RegularPlayDefensivePlayCall defensivePlayCall
  ) {
    var defensivePersonnel = defensivePlayCall.personnel();
    var target = offensiveTargetDecider.target(
      offensivePlayCall,
      defensivePersonnel
    );
    var offensiveDecision = new RegularPlayOffensiveDecision(
      offensivePlayCall.personnel(),
      target
    );
    var playArguments = new RegularPlaySimulatorArguments(
      offensiveDecision,
      defensivePersonnel
    );
    return switch (offensivePlayCall.type()) {
      case PASS -> passingSimulator.simulate(playArguments);
      case RUSH -> rushingSimulator.simulate(playArguments);
    };
  }
}
