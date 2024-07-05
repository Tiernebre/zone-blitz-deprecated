package com.tiernebre.game_simulation.game.play.regular.offensive_target;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.game.play.regular.RegularPlayOffensivePlayCall;
import com.tiernebre.game_simulation.personnel.defense.RegularPlayDefensivePersonnel;

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
