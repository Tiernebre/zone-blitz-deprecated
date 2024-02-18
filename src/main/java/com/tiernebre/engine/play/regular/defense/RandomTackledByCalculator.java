package com.tiernebre.engine.play.regular.defense;

import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.engine.dto.personnel.RegularPlayOffensivePersonnel;
import com.tiernebre.engine.play.regular.RegularPlayPersonnelUtils;
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
