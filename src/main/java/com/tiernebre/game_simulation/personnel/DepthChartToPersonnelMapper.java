package com.tiernebre.game_simulation.personnel;

import com.tiernebre.game_simulation.dto.personnel.OffensiveDepthChart;
import com.tiernebre.game_simulation.dto.personnel.PuntOffensivePersonnel;

public interface DepthChartToPersonnelMapper {
  public PuntOffensivePersonnel toPunt(OffensiveDepthChart depthChart);
}
