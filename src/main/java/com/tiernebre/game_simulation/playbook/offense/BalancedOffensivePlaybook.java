package com.tiernebre.game_simulation.playbook.offense;

import com.tiernebre.game_simulation.dto.game.Down;
import com.tiernebre.game_simulation.dto.game.GameState;
import com.tiernebre.game_simulation.dto.personnel.OffensiveDepthChart;
import com.tiernebre.game_simulation.play.call.OffensivePlayCall;
import com.tiernebre.game_simulation.play.punt.PuntOffensivePlaycall;
import com.tiernebre.game_simulation.playbook.offense.personnel.DepthChartToPersonnelMapper;

public final class BalancedOffensivePlaybook implements OffensivePlaybook {

  private final DepthChartToPersonnelMapper depthChartToPersonnelMapper;

  public BalancedOffensivePlaybook(
    DepthChartToPersonnelMapper depthChartToPersonnelMapper
  ) {
    this.depthChartToPersonnelMapper = depthChartToPersonnelMapper;
  }

  @Override
  public OffensivePlayCall call(
    GameState gameState,
    OffensiveDepthChart depthChart
  ) {
    if (gameState.drive().down() == Down.FOURTH) {
      return new PuntOffensivePlaycall(
        depthChartToPersonnelMapper.toPunt(depthChart)
      );
    }
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'call'");
  }
}
