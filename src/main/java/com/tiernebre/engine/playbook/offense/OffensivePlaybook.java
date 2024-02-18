package com.tiernebre.engine.playbook.offense;

import com.tiernebre.engine.dto.game.GameState;
import com.tiernebre.engine.dto.personnel.OffensiveDepthChart;
import com.tiernebre.engine.play.call.OffensivePlayCall;

public interface OffensivePlaybook {
  public OffensivePlayCall call(
    GameState gameState,
    OffensiveDepthChart depthChart
  );
}
