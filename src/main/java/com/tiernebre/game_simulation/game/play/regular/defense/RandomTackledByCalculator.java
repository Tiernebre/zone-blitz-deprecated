package com.tiernebre.game_simulation.game.play.regular.defense;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.personnel.defense.RegularPlayDefensivePersonnel;
import com.tiernebre.game_simulation.personnel.offense.RegularPlayOffensivePersonnel;
import java.util.Random;

public class RandomTackledByCalculator implements TackledByCalculator {

  private final Random random;

  public RandomTackledByCalculator(Random random) {
    this.random = random;
  }

  @Override
  public Player tackledBy(RegularPlayDefensivePersonnel defense) {
    return tackledBy(defense.players());
  }

  @Override
  public Player tackledBy(RegularPlayOffensivePersonnel offense) {
    return tackledBy(offense.players());
  }

  private Player tackledBy(Player[] players) {
    return players[random.nextInt(players.length)];
  }
}
