package com.tiernebre.game_simulation.play.regular.defense;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.game_simulation.play.regular.RegularPlayPersonnelUtils;
import com.tiernebre.game_simulation.playbook.offense.personnel.RegularPlayOffensivePersonnel;
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
    return tackledBy(offense.players());
  }

  private Player tackledBy(Player[] players) {
    return players[random.nextInt(players.length)];
  }
}
