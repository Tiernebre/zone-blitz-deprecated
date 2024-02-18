package com.tiernebre.engine.game;

import com.tiernebre.engine.dto.game.Game;
import com.tiernebre.engine.game.state.GameStateMachine;
import com.tiernebre.engine.play.PlaySimulator;
import com.tiernebre.engine.play.PlaySimulatorArguments;

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
