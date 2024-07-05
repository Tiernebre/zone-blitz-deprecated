package com.tiernebre.game_simulation.playbook.defense;

import com.tiernebre.game_simulation.dto.game.GameState;
import com.tiernebre.game_simulation.dto.personnel.DefensiveDepthChart;
import com.tiernebre.game_simulation.game.play.call.DefensivePlayCall;

public interface DefensivePlaybook {
  public DefensivePlayCall call(
    GameState gameState,
    DefensiveDepthChart depthChart
  );
}
