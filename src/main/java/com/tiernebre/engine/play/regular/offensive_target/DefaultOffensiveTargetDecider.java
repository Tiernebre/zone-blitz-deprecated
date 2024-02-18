package com.tiernebre.engine.play.regular.offensive_target;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.engine.play.regular.RegularPlayOffensivePlayCall;

public final class DefaultOffensiveTargetDecider
  implements OffensiveTargetDecider {

  private final RushingTargetDecider rushingTargetDecider;

  public DefaultOffensiveTargetDecider(
    RushingTargetDecider rushingTargetDecider
  ) {
    this.rushingTargetDecider = rushingTargetDecider;
  }

  @Override
  public Player target(
    RegularPlayOffensivePlayCall offensivePlayCall,
    RegularPlayDefensivePersonnel defensivePersonnel
  ) {
    return rushingTargetDecider.target(
      offensivePlayCall.personnel(),
      defensivePersonnel
    );
  }
}
