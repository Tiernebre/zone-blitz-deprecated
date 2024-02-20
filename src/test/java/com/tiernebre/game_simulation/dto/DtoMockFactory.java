package com.tiernebre.game_simulation.dto;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.PlayerAttributes;
import com.tiernebre.game_simulation.dto.Team;

public class DtoMockFactory {

  public static Team team() {
    return new Team(
      "Chicago",
      "Blazers",
      new Player[] { player() },
      null,
      null
    );
  }

  public static Player player(int attribute) {
    return new Player("Joe", "Bob", 0, playerAttributes(attribute));
  }

  public static Player player() {
    return new Player("Joe", "Bob", 0, playerAttributes(0));
  }

  public static PlayerAttributes playerAttributes(int attribute) {
    return new PlayerAttributes(
      attribute,
      attribute,
      attribute,
      attribute,
      attribute,
      attribute
    );
  }
}
