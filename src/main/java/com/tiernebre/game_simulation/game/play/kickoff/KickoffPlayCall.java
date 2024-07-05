package com.tiernebre.game_simulation.game.play.kickoff;

import com.tiernebre.game_simulation.dto.personnel.KickoffPersonnel;
import com.tiernebre.game_simulation.game.play.call.DefensivePlayCall;

public final class KickoffPlayCall extends DefensivePlayCall {

  private final KickoffPersonnel personnel;

  public KickoffPlayCall(KickoffPersonnel personnel) {
    this.personnel = personnel;
  }

  public KickoffPersonnel personnel() {
    return personnel;
  }
}
