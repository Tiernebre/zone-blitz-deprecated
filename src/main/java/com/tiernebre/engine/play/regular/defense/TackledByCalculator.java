package com.tiernebre.engine.play.regular.defense;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.engine.dto.personnel.RegularPlayOffensivePersonnel;

public interface TackledByCalculator {
  public Player tackledBy(RegularPlayDefensivePersonnel defense);

  public Player tackledBy(RegularPlayOffensivePersonnel offense);
}
