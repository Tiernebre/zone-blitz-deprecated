package com.tiernebre.game_simulation.play.regular;

import com.tiernebre.game_simulation.play.call.DefensivePlayCall;
import com.tiernebre.game_simulation.playbook.defense.RegularPlayDefensivePersonnel;

public class RegularPlayDefensivePlayCall extends DefensivePlayCall {

  private final RegularPlayDefensivePersonnel personnel;

  public RegularPlayDefensivePlayCall(RegularPlayDefensivePersonnel personnel) {
    this.personnel = personnel;
  }

  public RegularPlayDefensivePersonnel personnel() {
    return personnel;
  }
}
