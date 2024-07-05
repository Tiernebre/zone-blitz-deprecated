package com.tiernebre.game_simulation.game.play.field_goal.distance;

import com.tiernebre.game_simulation.dto.Player;

public interface FieldGoalDistanceCalculator {
  public int calculate(Player kicker);
}
