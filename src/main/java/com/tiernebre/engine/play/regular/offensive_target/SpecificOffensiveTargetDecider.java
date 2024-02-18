package com.tiernebre.engine.play.regular.offensive_target;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.engine.dto.personnel.RegularPlayOffensivePersonnel;

public interface SpecificOffensiveTargetDecider {
  public Player target(
    RegularPlayOffensivePersonnel offensivePersonnel,
    RegularPlayDefensivePersonnel defensivePersonnel
  );
}
