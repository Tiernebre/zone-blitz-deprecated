package com.tiernebre.game_simulation.playbook.offense;

import com.tiernebre.game_simulation.dto.game.GameState;
import com.tiernebre.game_simulation.dto.personnel.OffensiveDepthChart;
import com.tiernebre.game_simulation.game.play.call.OffensivePlayCall;

public interface OffensivePlaybook {
  public OffensivePlayCall call(
    GameState gameState,
    OffensiveDepthChart depthChart
  );
}
