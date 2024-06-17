package com.tiernebre.game_simulation.play.regular.offensive_target;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.play.regular.RegularPlayOffensivePlayCall;
import com.tiernebre.game_simulation.playbook.defense.RegularPlayDefensivePersonnel;

public interface OffensiveTargetDecider {
  public Player target(
    RegularPlayOffensivePlayCall offensivePlayCall,
    RegularPlayDefensivePersonnel defensivePersonnel
  );
}
