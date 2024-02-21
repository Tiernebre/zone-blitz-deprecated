package com.tiernebre.game_simulation.play.regular.turnover;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.personnel.RegularPlayDefensivePersonnel;

public interface FumbleCalculator {
  public boolean occurred(
    Player carrier,
    RegularPlayDefensivePersonnel defense
  );
}
