package com.tiernebre.engine.play.punt;

import com.tiernebre.engine.dto.personnel.PuntDefensivePersonnel;
import com.tiernebre.engine.play.call.DefensivePlayCall;

public final class PuntDefensivePlayCall extends DefensivePlayCall {

  private final PuntDefensivePersonnel personnel;

  public PuntDefensivePlayCall(PuntDefensivePersonnel personnel) {
    this.personnel = personnel;
  }

  public PuntDefensivePersonnel personnel() {
    return personnel;
  }
}
