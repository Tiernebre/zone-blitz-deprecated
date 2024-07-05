package com.tiernebre.game_simulation.game.play.regular;

import com.tiernebre.game_simulation.game.play.call.DefensivePlayCall;
import com.tiernebre.game_simulation.personnel.defense.RegularPlayDefensivePersonnel;

public class RegularPlayDefensivePlayCall extends DefensivePlayCall {

  private final RegularPlayDefensivePersonnel personnel;

  public RegularPlayDefensivePlayCall(RegularPlayDefensivePersonnel personnel) {
    this.personnel = personnel;
  }

  public RegularPlayDefensivePersonnel personnel() {
    return personnel;
  }
}
