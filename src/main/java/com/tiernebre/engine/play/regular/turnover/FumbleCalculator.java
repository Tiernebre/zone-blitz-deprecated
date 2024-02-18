package com.tiernebre.engine.play.regular.turnover;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.dto.personnel.RegularPlayDefensivePersonnel;

public interface FumbleCalculator {
  public boolean occurred(
    Player carrier,
    RegularPlayDefensivePersonnel defense
  );
}
