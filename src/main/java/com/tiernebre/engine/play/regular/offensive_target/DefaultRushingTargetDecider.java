package com.tiernebre.engine.play.regular.offensive_target;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.engine.dto.personnel.RegularPlayOffensivePersonnel;

public final class DefaultRushingTargetDecider implements RushingTargetDecider {

  @Override
  public Player target(
    RegularPlayOffensivePersonnel offensivePersonnel,
    RegularPlayDefensivePersonnel defensivePersonnel
  ) {
    return offensivePersonnel.runningBacks().length == 0
      ? offensivePersonnel.quarterback()
      : offensivePersonnel.runningBacks()[0];
  }
}
