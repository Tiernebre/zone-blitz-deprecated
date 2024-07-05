package com.tiernebre.game_simulation.game.play;

public final class PlaySimulatorFactory {

  public PlaySimulator create() {
    return new DefaultPlaySimulator(null, null, null, null);
  }
}
