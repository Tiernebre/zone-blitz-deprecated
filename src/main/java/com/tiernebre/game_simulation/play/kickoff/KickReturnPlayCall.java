package com.tiernebre.game_simulation.play.kickoff;

import com.tiernebre.game_simulation.dto.personnel.KickReturnPersonnel;
import com.tiernebre.game_simulation.play.call.OffensivePlayCall;

public final class KickReturnPlayCall extends OffensivePlayCall {

  private final KickReturnPersonnel personnel;

  public KickReturnPlayCall(KickReturnPersonnel personnel) {
    this.personnel = personnel;
  }

  public KickReturnPersonnel personnel() {
    return personnel;
  }
}
