package com.tiernebre.engine.play.kickoff;

import com.tiernebre.engine.dto.personnel.KickoffPersonnel;
import com.tiernebre.engine.play.call.DefensivePlayCall;

public final class KickoffPlayCall extends DefensivePlayCall {

  private final KickoffPersonnel personnel;

  public KickoffPlayCall(KickoffPersonnel personnel) {
    this.personnel = personnel;
  }

  public KickoffPersonnel personnel() {
    return personnel;
  }
}
