package com.tiernebre.game_simulation.game.state;

import com.tiernebre.game_simulation.dto.game.Game;
import com.tiernebre.game_simulation.game.play.PlayResult;
import com.tiernebre.game_simulation.game.play.field_goal.result.FieldGoalResult;
import com.tiernebre.game_simulation.game.play.kickoff.KickoffResult;
import com.tiernebre.game_simulation.game.play.punt.PuntResult;
import com.tiernebre.game_simulation.game.play.regular.RegularPlayResult;
import com.tiernebre.game_simulation.game.state.handlers.field_goal.FieldGoalHandler;
import com.tiernebre.game_simulation.game.state.handlers.kickoff.KickoffHandler;
import com.tiernebre.game_simulation.game.state.handlers.punt.PuntHandler;
import com.tiernebre.game_simulation.game.state.handlers.regular.RegularPlayHandler;

public final class DefaultGameStateMachine implements GameStateMachine {

  private final RegularPlayHandler regularPlayHandler;
  private final FieldGoalHandler fieldGoalHandler;
  private final PuntHandler puntHandler;
  private final KickoffHandler kickoffHandler;

  public DefaultGameStateMachine(
    RegularPlayHandler regularPlayHandler,
    FieldGoalHandler fieldGoalHandler,
    PuntHandler puntHandler,
    KickoffHandler kickoffHandler
  ) {
    this.regularPlayHandler = regularPlayHandler;
    this.fieldGoalHandler = fieldGoalHandler;
    this.puntHandler = puntHandler;
    this.kickoffHandler = kickoffHandler;
  }

  @Override
  public Game handlePlayResult(Game game, PlayResult playResult) {
    var state = game.state();
    var newState =
      switch (playResult) {
        case RegularPlayResult regularPlayResult -> regularPlayHandler.handle(
          state,
          regularPlayResult
        );
        case FieldGoalResult fieldGoalResult -> fieldGoalHandler.handle(
          state,
          fieldGoalResult
        );
        case PuntResult puntResult -> puntHandler.handle(state, puntResult);
        case KickoffResult kickoffResult -> kickoffHandler.handle(
          state,
          kickoffResult
        );
        default -> null;
      };
    return new Game(newState, game.home(), game.away());
  }
}
