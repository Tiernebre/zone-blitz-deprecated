package com.tiernebre.game_simulation.play.regular.defense;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.game_simulation.dto.personnel.RegularPlayOffensivePersonnel;
import com.tiernebre.game_simulation.play.regular.RegularPlayPersonnelUtils;
import java.util.Random;

public class RandomTackledByCalculator implements TackledByCalculator {

  private final Random random;

  public RandomTackledByCalculator(Random random) {
    this.random = random;
  }

  @Override
  public Player tackledBy(RegularPlayDefensivePersonnel defense) {
    return tackledBy(RegularPlayPersonnelUtils.getAllPlayers(defense));
  }

  @Override
  public Player tackledBy(RegularPlayOffensivePersonnel offense) {
    return tackledBy(RegularPlayPersonnelUtils.getAllPlayers(offense));
  }

  private Player tackledBy(Player[] players) {
    return players[random.nextInt(players.length)];
  }
}
