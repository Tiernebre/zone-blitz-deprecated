package com.tiernebre.game_simulation.playbook.offense.personnel;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.personnel.OffensiveLine;

public abstract class RegularOffensivePlayPersonnel {

  protected final Player quarterback;
  protected final Player[] runningBacks;
  protected final Player[] tightEnds;
  protected final Player[] wideReceivers;
  protected final OffensiveLine offensiveLine;

  public RegularOffensivePlayPersonnel(
    Player quarterback,
    Player[] runningBacks,
    Player[] tightEnds,
    Player[] wideReceivers,
    OffensiveLine offensiveLine
  ) {
    this.quarterback = quarterback;
    this.runningBacks = runningBacks;
    this.tightEnds = tightEnds;
    this.wideReceivers = wideReceivers;
    this.offensiveLine = offensiveLine;
  }
}
