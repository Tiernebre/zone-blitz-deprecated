package com.tiernebre.game_simulation.game;

import com.tiernebre.game_simulation.dto.game.Game;
import com.tiernebre.game_simulation.game.state.GameStateMachine;
import com.tiernebre.game_simulation.play.PlaySimulator;
import com.tiernebre.game_simulation.play.PlaySimulatorArguments;

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
    var offensivePlayCall = offense
      .coachingStaff()
      .headCoach()
      .offensivePlaybook()
      .call(state, offense.depthChart().offense());
    var defensivePlayCall = defense
      .coachingStaff()
      .headCoach()
      .defensivePlaybook()
      .call(state, defense.depthChart().defense());
    var result = playSimulator.simulate(
      new PlaySimulatorArguments(offensivePlayCall, defensivePlayCall)
    );
    return gameStateMachine.handlePlayResult(game, result);
  }
}
