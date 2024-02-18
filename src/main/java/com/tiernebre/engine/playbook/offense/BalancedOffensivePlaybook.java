package com.tiernebre.engine.playbook.offense;

import com.tiernebre.engine.dto.game.Down;
import com.tiernebre.engine.dto.game.GameState;
import com.tiernebre.engine.dto.personnel.OffensiveDepthChart;
import com.tiernebre.engine.play.call.OffensivePlayCall;
import com.tiernebre.engine.play.punt.PuntOffensivePlaycall;
import com.tiernebre.engine.playbook.offense.personnel.DepthChartToPersonnelMapper;

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
