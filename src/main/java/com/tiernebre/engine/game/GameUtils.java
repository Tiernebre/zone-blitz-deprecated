package com.tiernebre.engine.game;

import com.tiernebre.engine.dto.Team;
import com.tiernebre.engine.dto.game.Game;
import com.tiernebre.engine.dto.game.Side;

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
