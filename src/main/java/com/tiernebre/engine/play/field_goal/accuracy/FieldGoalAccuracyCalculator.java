package com.tiernebre.engine.play.field_goal.accuracy;

import com.tiernebre.engine.dto.Player;

public interface FieldGoalAccuracyCalculator {
  public boolean made(Player kicker);
}
