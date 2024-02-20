package com.tiernebre.game_simulation.play.punt;

import com.tiernebre.game_simulation.dto.Player;

public interface PuntAccuracyCalculator {
  public boolean beforeEndzone(Player punter);
}
