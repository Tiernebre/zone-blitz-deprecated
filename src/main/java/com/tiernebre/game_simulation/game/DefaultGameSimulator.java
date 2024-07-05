package com.tiernebre.game_simulation.game;

import com.tiernebre.game_simulation.dto.game.Game;
import com.tiernebre.game_simulation.game.play.PlaySimulator;
import com.tiernebre.game_simulation.game.play.PlaySimulatorArguments;
import com.tiernebre.game_simulation.game.state.GameStateMachine;

public final class DefaultGameSimulator implements GameSimulator {

  private final PlaySimulator playSimulator;
  private final GameStateMachine gameStateMachine;

  public DefaultGameSimulator(
    PlaySimulator playSimulator,
    GameStateMachine gameStateMachine
  ) {
    this.playSimulator = playSimulator;
    this.gameStateMachine = gameStateMachine;
  }

  @Override
  public Game simulate(Game game) {
    var state = game.state();
    var offense = GameUtils.offensiveTeam(game);
    var defense = GameUtils.defensiveTeam(game);
    return gameStateMachine.handlePlayResult(
      game,
      playSimulator.simulate(
        new PlaySimulatorArguments(
          offense
            .coachingStaff()
            .headCoach()
            .offensivePlaybook()
            .call(state, offense.depthChart().offense()),
          defense
            .coachingStaff()
            .headCoach()
            .defensivePlaybook()
            .call(state, defense.depthChart().defense())
        )
      )
    );
  }
}
