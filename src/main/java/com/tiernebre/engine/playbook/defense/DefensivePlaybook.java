package com.tiernebre.engine.playbook.defense;

import com.tiernebre.engine.dto.game.GameState;
import com.tiernebre.engine.dto.personnel.DefensiveDepthChart;
import com.tiernebre.engine.play.call.DefensivePlayCall;

public interface DefensivePlaybook {
  public DefensivePlayCall call(
    GameState gameState,
    DefensiveDepthChart depthChart
  );
}
