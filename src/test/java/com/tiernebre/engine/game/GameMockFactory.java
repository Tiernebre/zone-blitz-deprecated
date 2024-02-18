package com.tiernebre.engine.game;

import com.tiernebre.engine.EngineConstants;
import com.tiernebre.engine.dto.DtoMockFactory;
import com.tiernebre.engine.dto.game.CoinTossDecision;
import com.tiernebre.engine.dto.game.Direction;
import com.tiernebre.engine.dto.game.Down;
import com.tiernebre.engine.dto.game.Drive;
import com.tiernebre.engine.dto.game.Game;
import com.tiernebre.engine.dto.game.GameScoreboard;
import com.tiernebre.engine.dto.game.GameState;
import com.tiernebre.engine.dto.game.GameTime;
import com.tiernebre.engine.dto.game.Side;
import java.util.Random;

public class GameMockFactory {

  private static final Random random = new Random();

  public static Game game() {
    return new Game(state(), DtoMockFactory.team(), DtoMockFactory.team());
  }

  public static GameState state() {
    return state(drive());
  }

  public static GameState randomizedState() {
    return new GameState(
      randomizedDrive(),
      null,
      null,
      null,
      null,
      false,
      false
    );
  }

  public static GameState state(Drive drive) {
    return new GameState(
      drive,
      score(),
      time(),
      CoinTossDecision.RECEIVE,
      Side.HOME,
      false,
      false
    );
  }

  public static Drive randomizedDrive() {
    var downs = Down.values();
    var directions = Direction.values();
    return new Drive(
      downs[random.nextInt(downs.length)],
      random.nextInt(EngineConstants.END_ZONE_YARD_LINE - 1),
      random.nextInt(EngineConstants.END_ZONE_YARD_LINE - 1),
      directions[random.nextInt(directions.length)]
    );
  }

  public static Drive drive() {
    return drive(0);
  }

  public static Drive drive(int lineOfScrimmage) {
    return new Drive(Down.FIRST, lineOfScrimmage, 10, Direction.EAST);
  }

  public static GameScoreboard score() {
    return new GameScoreboard(0, 0);
  }

  public static GameTime time() {
    return new GameTime(60 * 60, 60 * 60);
  }
}
