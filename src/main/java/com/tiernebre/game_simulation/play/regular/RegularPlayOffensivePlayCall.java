package com.tiernebre.game_simulation.play.regular;

import com.tiernebre.game_simulation.play.call.OffensivePlayCall;
import com.tiernebre.game_simulation.playbook.offense.personnel.RegularPlayOffensivePersonnel;

public final class RegularPlayOffensivePlayCall extends OffensivePlayCall {

  private final RegularPlayOffensivePersonnel personnel;
  private final RegularPlayType type;

  public RegularPlayOffensivePlayCall(
    RegularPlayOffensivePersonnel personnel,
    RegularPlayType type
  ) {
    this.personnel = personnel;
    this.type = type;
  }

  public RegularPlayOffensivePersonnel personnel() {
    return personnel;
  }

  public RegularPlayType type() {
    return type;
  }
}
