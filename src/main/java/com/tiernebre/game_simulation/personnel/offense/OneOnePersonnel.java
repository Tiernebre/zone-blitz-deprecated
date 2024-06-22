package com.tiernebre.game_simulation.personnel.offense;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.personnel.OffensiveLine;

public final class OneOnePersonnel extends RegularPlayOffensivePersonnel {

  public OneOnePersonnel(
    Player quarterback,
    Player runningBack,
    Player tightEnd,
    Player splitEnd,
    Player flanker,
    Player slot,
    OffensiveLine offensiveLine
  ) {
    super(
      quarterback,
      new Player[] { runningBack },
      new Player[] { tightEnd },
      new Player[] { splitEnd, flanker, slot },
      offensiveLine
    );
  }
}
