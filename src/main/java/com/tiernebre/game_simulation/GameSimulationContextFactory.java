package com.tiernebre.game_simulation;

import com.tiernebre.game_simulation.game.DefaultGameSimulator;
import com.tiernebre.game_simulation.game.play.PlaySimulatorFactory;
import com.tiernebre.game_simulation.game.state.GameStateMachineFactory;

public final class GameSimulationContextFactory {

  public GameSimulationContext create() {
    return new GameSimulationContext(
      new DefaultGameSimulator(
        new PlaySimulatorFactory().create(),
        new GameStateMachineFactory().create()
      )
    );
  }
}
