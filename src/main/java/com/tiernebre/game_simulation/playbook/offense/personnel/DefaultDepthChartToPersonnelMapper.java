package com.tiernebre.game_simulation.playbook.offense.personnel;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.personnel.OffensiveDepthChart;
import com.tiernebre.game_simulation.dto.personnel.PuntOffensivePersonnel;

public class DefaultDepthChartToPersonnelMapper
  implements DepthChartToPersonnelMapper {

  @Override
  public PuntOffensivePersonnel toPunt(OffensiveDepthChart depthChart) {
    return new PuntOffensivePersonnel(
      depthChart.punters()[0],
      depthChart.longSnappers()[0],
      new Player[] {},
      new Player[] {}
    );
  }
}
