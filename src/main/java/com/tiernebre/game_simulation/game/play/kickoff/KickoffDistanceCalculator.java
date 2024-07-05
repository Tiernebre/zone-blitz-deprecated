package com.tiernebre.game_simulation.game.play.kickoff;

import com.tiernebre.game_simulation.dto.Player;

public interface KickoffDistanceCalculator {
  public int calculate(Player kicker);
}
