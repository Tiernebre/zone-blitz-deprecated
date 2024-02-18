package com.tiernebre.engine.play.field_goal.distance;

import com.tiernebre.engine.dto.Player;

public interface FieldGoalDistanceCalculator {
  public int calculate(Player kicker);
}
