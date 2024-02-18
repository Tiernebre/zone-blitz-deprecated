package com.tiernebre.engine.play.punt;

import com.tiernebre.engine.dto.personnel.PuntOffensivePersonnel;
import com.tiernebre.engine.play.call.OffensivePlayCall;

public final class PuntOffensivePlaycall extends OffensivePlayCall {

  private final PuntOffensivePersonnel personnel;

  public PuntOffensivePlaycall(PuntOffensivePersonnel personnel) {
    this.personnel = personnel;
  }

  public PuntOffensivePersonnel personnel() {
    return personnel;
  }
}
