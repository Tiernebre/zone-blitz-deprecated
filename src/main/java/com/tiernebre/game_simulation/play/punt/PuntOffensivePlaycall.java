package com.tiernebre.game_simulation.play.punt;

import com.tiernebre.game_simulation.dto.personnel.PuntOffensivePersonnel;
import com.tiernebre.game_simulation.play.call.OffensivePlayCall;

public final class PuntOffensivePlaycall extends OffensivePlayCall {

  private final PuntOffensivePersonnel personnel;

  public PuntOffensivePlaycall(PuntOffensivePersonnel personnel) {
    this.personnel = personnel;
  }

  public PuntOffensivePersonnel personnel() {
    return personnel;
  }
}
