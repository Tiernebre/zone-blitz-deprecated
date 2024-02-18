package com.tiernebre.engine.play.regular;

import com.tiernebre.engine.dto.personnel.RegularPlayOffensivePersonnel;
import com.tiernebre.engine.play.call.OffensivePlayCall;

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
