package com.tiernebre.engine.playbook.offense.personnel;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.dto.personnel.OffensiveDepthChart;
import com.tiernebre.engine.dto.personnel.PuntOffensivePersonnel;

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
