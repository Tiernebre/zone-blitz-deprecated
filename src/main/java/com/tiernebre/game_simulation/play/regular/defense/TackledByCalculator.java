package com.tiernebre.game_simulation.play.regular.defense;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.game_simulation.playbook.offense.personnel.RegularPlayOffensivePersonnel;

public interface TackledByCalculator {
  public Player tackledBy(RegularPlayDefensivePersonnel defense);

  public Player tackledBy(RegularPlayOffensivePersonnel offense);
}
