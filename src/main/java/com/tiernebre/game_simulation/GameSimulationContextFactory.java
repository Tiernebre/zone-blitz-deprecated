package com.tiernebre.game_simulation;

import com.tiernebre.game_simulation.dto.game.ScoringConfiguration;
import com.tiernebre.game_simulation.game.DefaultGameSimulator;
import com.tiernebre.game_simulation.game.state.DefaultGameStateMachine;
import com.tiernebre.game_simulation.game.state.handlers.field_goal.DefaultFieldGoalHandler;
import com.tiernebre.game_simulation.game.state.handlers.punt.DefaultPuntHandler;
import com.tiernebre.game_simulation.game.state.handlers.regular.DefaultRegularPlayHandler;
import com.tiernebre.game_simulation.game.state.handlers.scoring.DefaultScoringPlayHandler;
import com.tiernebre.game_simulation.game.state.handlers.turnover.DefaultTurnoverHandler;

public class GameSimulationContextFactory {

  public GameSimulationContext create() {
    var scoringConfiguration = new ScoringConfiguration(7, 3, 1, 2, 0);
    var scoringPlayHandler = new DefaultScoringPlayHandler(
      scoringConfiguration
    );
    var turnoverHandler = new DefaultTurnoverHandler();
    var regularPlayHandler = new DefaultRegularPlayHandler(
      scoringPlayHandler,
      turnoverHandler
    );
    var fieldGoalHandler = new DefaultFieldGoalHandler(
      turnoverHandler,
      scoringPlayHandler
    );
    var puntHandler = new DefaultPuntHandler();
    var gameStateMachine = new DefaultGameStateMachine(
      regularPlayHandler,
      fieldGoalHandler,
      puntHandler,
      // TODO: Implement kickoff handler
      null
    );

    var gameSimulator = new DefaultGameSimulator(null, gameStateMachine);
    return new GameSimulationContext(gameSimulator);
  }
}
