package com.tiernebre.game_simulation.game.play.field_goal.accuracy;

import com.tiernebre.game_simulation.dto.Player;

public interface FieldGoalAccuracyCalculator {
  public boolean made(Player kicker);
}
