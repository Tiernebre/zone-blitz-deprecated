package com.tiernebre.game_simulation.game.play.regular.offensive_target;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.game.play.regular.RegularPlayOffensivePlayCall;
import com.tiernebre.game_simulation.personnel.defense.RegularPlayDefensivePersonnel;

public interface OffensiveTargetDecider {
  public Player target(
    RegularPlayOffensivePlayCall offensivePlayCall,
    RegularPlayDefensivePersonnel defensivePersonnel
  );
}
