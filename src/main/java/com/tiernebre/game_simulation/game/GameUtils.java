package com.tiernebre.game_simulation.game;

import com.tiernebre.game_simulation.dto.Team;
import com.tiernebre.game_simulation.dto.game.Game;
import com.tiernebre.game_simulation.dto.game.Side;

public class GameUtils {

  public static Team offensiveTeam(Game game) {
    return (game.state().onOffense() == Side.HOME) ? game.home() : game.away();
  }

  public static Team defensiveTeam(Game game) {
    return (offensiveTeam(game).equals(game.home()))
      ? game.away()
      : game.home();
  }
}
