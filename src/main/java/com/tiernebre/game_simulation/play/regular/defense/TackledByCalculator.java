package com.tiernebre.game_simulation.play.regular.defense;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.personnel.defense.RegularPlayDefensivePersonnel;
import com.tiernebre.game_simulation.personnel.offense.RegularPlayOffensivePersonnel;

public interface TackledByCalculator {
  public Player tackledBy(RegularPlayDefensivePersonnel defense);

  public Player tackledBy(RegularPlayOffensivePersonnel offense);
}
