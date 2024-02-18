package com.tiernebre.engine.play.kickoff;

import com.tiernebre.engine.dto.personnel.KickReturnPersonnel;
import com.tiernebre.engine.play.call.OffensivePlayCall;

public final class KickReturnPlayCall extends OffensivePlayCall {

  private final KickReturnPersonnel personnel;

  public KickReturnPlayCall(KickReturnPersonnel personnel) {
    this.personnel = personnel;
  }

  public KickReturnPersonnel personnel() {
    return personnel;
  }
}
