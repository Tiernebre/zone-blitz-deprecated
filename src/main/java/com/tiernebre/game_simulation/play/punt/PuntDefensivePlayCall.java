package com.tiernebre.game_simulation.play.punt;

import com.tiernebre.game_simulation.dto.personnel.PuntDefensivePersonnel;
import com.tiernebre.game_simulation.play.call.DefensivePlayCall;

public final class PuntDefensivePlayCall extends DefensivePlayCall {

  private final PuntDefensivePersonnel personnel;

  public PuntDefensivePlayCall(PuntDefensivePersonnel personnel) {
    this.personnel = personnel;
  }

  public PuntDefensivePersonnel personnel() {
    return personnel;
  }
}
