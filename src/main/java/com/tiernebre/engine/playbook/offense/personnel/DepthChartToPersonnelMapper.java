package com.tiernebre.engine.playbook.offense.personnel;

import com.tiernebre.engine.dto.personnel.OffensiveDepthChart;
import com.tiernebre.engine.dto.personnel.PuntOffensivePersonnel;

public interface DepthChartToPersonnelMapper {
  public PuntOffensivePersonnel toPunt(OffensiveDepthChart depthChart);
}
