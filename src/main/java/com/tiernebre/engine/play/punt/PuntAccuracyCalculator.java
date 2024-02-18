package com.tiernebre.engine.play.punt;

import com.tiernebre.engine.dto.Player;

public interface PuntAccuracyCalculator {
  public boolean beforeEndzone(Player punter);
}
