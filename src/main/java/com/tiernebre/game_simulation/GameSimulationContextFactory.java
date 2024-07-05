package com.tiernebre.game_simulation;

import com.tiernebre.game_simulation.game.DefaultGameSimulator;
import com.tiernebre.game_simulation.game.state.GameStateMachineFactory;

public final class GameSimulationContextFactory {

  public GameSimulationContext create() {
    return new GameSimulationContext(
      // TODO: inject a play simulator
      new DefaultGameSimulator(null, new GameStateMachineFactory().create())
    );
  }
}
