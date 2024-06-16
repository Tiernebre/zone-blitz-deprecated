package com.tiernebre.game_simulation.play.regular.offensive_target;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.game_simulation.playbook.offense.personnel.RegularPlayOffensivePersonnel;

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
