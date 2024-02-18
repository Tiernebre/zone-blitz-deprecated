package com.tiernebre.engine.play.regular.offensive_target;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.engine.play.regular.RegularPlayOffensivePlayCall;

public interface OffensiveTargetDecider {
  public Player target(
    RegularPlayOffensivePlayCall offensivePlayCall,
    RegularPlayDefensivePersonnel defensivePersonnel
  );
}
