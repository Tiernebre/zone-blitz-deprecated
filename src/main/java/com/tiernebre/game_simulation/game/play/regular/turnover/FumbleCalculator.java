package com.tiernebre.game_simulation.game.play.regular.turnover;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.personnel.defense.RegularPlayDefensivePersonnel;

public interface FumbleCalculator {
  public boolean occurred(
    Player carrier,
    RegularPlayDefensivePersonnel defense
  );
}
