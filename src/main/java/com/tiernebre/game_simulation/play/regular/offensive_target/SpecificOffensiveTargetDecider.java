package com.tiernebre.game_simulation.play.regular.offensive_target;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.playbook.defense.RegularPlayDefensivePersonnel;
import com.tiernebre.game_simulation.playbook.offense.personnel.RegularPlayOffensivePersonnel;

public interface SpecificOffensiveTargetDecider {
  public Player target(
    RegularPlayOffensivePersonnel offensivePersonnel,
    RegularPlayDefensivePersonnel defensivePersonnel
  );
}
