package com.tiernebre.engine.play.regular;

import com.tiernebre.engine.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.engine.play.call.DefensivePlayCall;

public class RegularPlayDefensivePlayCall extends DefensivePlayCall {

  private final RegularPlayDefensivePersonnel personnel;

  public RegularPlayDefensivePlayCall(RegularPlayDefensivePersonnel personnel) {
    this.personnel = personnel;
  }

  public RegularPlayDefensivePersonnel personnel() {
    return personnel;
  }
}
